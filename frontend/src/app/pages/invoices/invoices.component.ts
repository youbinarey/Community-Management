import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { InvoiceService } from '../../service/invoice-service.service';
import { CommonModule, NgFor } from '@angular/common';
import { InvoiceCommunity } from '../../models/InvoiceCommunity';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDialog } from '@angular/material/dialog';
import { AddInvoiceDialogComponent } from '../../components/invoices/add-invoice-dialog.component';

@Component({
  selector: 'app-invoices',
  templateUrl: './invoices.component.html',
  styleUrls: ['./invoices.component.scss'],
  imports: [NgFor, CommonModule],
})

/**
 * Component for managing and displaying invoices for a specific community.
 * 
 * - Fetches invoices based on the community ID from the route parameters.
 * - Allows downloading invoice PDFs.
 * - Opens a dialog to add new invoices and updates the list upon creation.
 */
export class InvoicesComponent implements OnInit {

  communityId!: number;
  invoices: InvoiceCommunity[] = [];
  newInvoice: any;

  constructor(
    private route: ActivatedRoute,
    private invoiceService: InvoiceService,
    private dialog: MatDialog
  ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.communityId = +params['communityId']; // Convertir a número
      this.loadInvoices();
    });
  }

  loadInvoices() {
    this.invoiceService.getInvoicesByCommunity(this.communityId).subscribe(data => {
      this.invoices = data;
    });
  }

  downloadInvoicePdf(invoiceId: number) {
    this.invoiceService.downloadCommunityInvoice(invoiceId).subscribe(blob => {
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = `recibo_comunidad_${invoiceId}.pdf`;
      a.click();
      window.URL.revokeObjectURL(url);
    }, error => {
      console.error('Error al descargar el PDF:', error);
    });
  }

  openAddInvoiceDialog() {
    const dialogRef = this.dialog.open(AddInvoiceDialogComponent, {
      width: '400px',
      maxHeight: '90vh',
      data: { communityId: this.communityId }
    });

    dialogRef.afterClosed().subscribe((result: InvoiceCommunity | undefined) => {
      if (result) {
        const { date, electricity, water, trash, elevator, maintenance, communityId } = result;

        this.invoiceService.createCommunityInvoice({
          date,
          electricity,
          water,
          trash,
          elevator,
          maintenance,
          communityId
        }).subscribe({
          next: () => this.loadInvoices(),
          error: (err) => console.error('Error al guardar factura:', err)
        });
      }
    });

  }

}

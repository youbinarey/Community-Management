import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { InvoiceService } from '../../service/invoice-service.service';
import { CommonModule, NgFor } from '@angular/common';
import { InvoiceCommunity } from '../../models/InvoiceCommunity copy';

@Component({
  selector: 'app-invoices',
  templateUrl: './invoices.component.html',
  styleUrls: ['./invoices.component.scss'],
  imports: [NgFor, CommonModule],
})
export class InvoicesComponent implements OnInit {



  communityId!: number;
  invoices: InvoiceCommunity[] = [];

  constructor(
    private route: ActivatedRoute,
    private invoiceService: InvoiceService,
  ) {}

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

 // TODO DESCARGAR PDF
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

}

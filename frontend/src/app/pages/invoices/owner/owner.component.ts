import { CommonModule, NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { InvoiceOwner } from '../../../models/InvoiceOwner';
import { InvoiceService } from '../../../service/invoice-service.service';

@Component({
  selector: 'app-owner',
  imports: [NgFor, CommonModule],
  templateUrl: './owner.component.html',
  styleUrl: './owner.component.scss'
})
export class OwnerInvoicesComponent implements OnInit {

   ownerId!: number;
  invoices: InvoiceOwner[] = []; 

  constructor(private route: ActivatedRoute, private invoiceService: InvoiceService) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.ownerId = +params['ownerId']; // Convertir a número
      console.log('ID del propietario:', this.ownerId);
    });
    console.log('Cargando recibos para propietario:', this.ownerId);
    this.loadInvoices();

  }

   loadInvoices() {
    this.invoiceService.getInvoicesByOwner(this.ownerId).subscribe({
      next: (data) => {
        this.invoices = data;
        console.log('Recibos cargados:', this.invoices);
      },
      error: (error) => {
        console.error('Error al cargar los recibos:', error);
      }
    });
  }

   downloadInvoice(invoiceId: number) {
    this.invoiceService.downloadOwnerInvoice(invoiceId).subscribe({
      next: (blob) => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `recibo_${invoiceId}.pdf`;
        a.click();
        window.URL.revokeObjectURL(url);
      },
      error: (error) => {
        console.error('Error al descargar el recibo:', error);
      }
    });
  }



}

 

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
/**
 * Component responsible for displaying and managing invoices for a specific owner.
 * 
 * - Retrieves the owner ID from the route parameters.
 * - Loads the list of invoices associated with the owner.
 * - Provides functionality to download invoices as PDF files.
 * - Allows sending invoices by email to a specified address.
 */
export class OwnerInvoicesComponent implements OnInit {
  ownerId!: number;
  invoices: InvoiceOwner[] = [];

  constructor(private route: ActivatedRoute, private invoiceService: InvoiceService) { }

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

  sendByEmail(invoiceId: number, email: string) {
    if (!email) {
      alert('No se encuentra el email del destinatario.');
      return;
    }

    this.invoiceService.sendByEmail(invoiceId, email)
      .subscribe({
        next: () => {
          alert('Factura enviada por email correctamente.');

        },
        error: err => {
          // Procesa el error para mostrar el mensaje más informativo
          let errorMsg = 'Desconocido';


          if (typeof err.error === 'string') {
            errorMsg = err.error;
          }

          else if (err.error && typeof err.error === 'object' && err.error.message) {
            errorMsg = err.error.message;
          }

          else if (err.message) {
            errorMsg = err.message;
          }

          else if (err.error) {
            errorMsg = JSON.stringify(err.error);
          }

          alert('Error al enviar la factura: ' + errorMsg);
        }
      });
  }

}



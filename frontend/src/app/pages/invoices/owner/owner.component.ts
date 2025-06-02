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

  sendByEmail(invoiceId: number, email: string) {
  if (!email) {
    alert('No se encuentra el email del destinatario.');
    return;
  }

  this.invoiceService.sendByEmail(invoiceId, email)
    .subscribe({
      next: () => {
        alert('Factura enviada por email correctamente.');
        // Aquí podrías usar un toast o resetear algún estado si lo necesitas
      },
      error: err => {
        // Procesa el error para mostrar el mensaje más informativo posible
        let errorMsg = 'Desconocido';

        // Si es texto plano (lo habitual con responseType: 'text')
        if (typeof err.error === 'string') {
          errorMsg = err.error;
        }
        // Si viene como objeto con un campo 'message'
        else if (err.error && typeof err.error === 'object' && err.error.message) {
          errorMsg = err.error.message;
        }
        // Si hay un mensaje general
        else if (err.message) {
          errorMsg = err.message;
        }
        // Si es otro tipo de objeto/error
        else if (err.error) {
          errorMsg = JSON.stringify(err.error);
        }

        alert('Error al enviar la factura: ' + errorMsg);
      }
    });
}




}

 

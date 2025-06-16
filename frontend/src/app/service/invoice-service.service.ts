import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { InvoiceCommunity } from "../models/InvoiceCommunity";
import { InvoiceOwner } from "../models/InvoiceOwner";

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {
  private apiUrl = 'http://localhost:8080/api/commune/invoices';

  constructor(private http: HttpClient) {}

  // Crear factura de comunidad
  createCommunityInvoice(invoice: {
    date: string;
    electricity: number;
    water: number;
    trash: number;
    elevator: number;
    maintenance: number;
    communityId: number;
  }): Observable<any> {
    return this.http.post(`${this.apiUrl}/create-community-invoice`, invoice);
  }

  // Obtener facturas por comunidad
  getInvoicesByCommunity(communityId: number): Observable<InvoiceCommunity[]> {
    return this.http.get<InvoiceCommunity[]>(`${this.apiUrl}/dto/community/${communityId}`);
  }

  // Descargar PDF de factura comunidad
  downloadCommunityInvoice(invoiceId: number): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/${invoiceId}/pdf`, { responseType: 'blob' });
  }

  // Obtener facturas por propietario
  getInvoicesByOwner(ownerId: number): Observable<InvoiceOwner[]> {
    return this.http.get<InvoiceOwner[]>(`http://localhost:8080/api/commune/owner-invoices/owner/${ownerId}`);
  }

  // Descargar PDF factura de propietario
  downloadOwnerInvoice(invoiceId: number): Observable<Blob> {
    return this.http.get(`http://localhost:8080/api/commune/invoices/owner-invoice/${invoiceId}/pdf`, {
      responseType: 'blob'
    });
  }

  // Enviar factura por correo
  sendByEmail(invoiceId: number, email: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/emails/owner-invoice/${invoiceId}/send`, null, {
      params: { email }
    });
  }
}

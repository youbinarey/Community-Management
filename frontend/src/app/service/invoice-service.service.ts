import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { InvoiceOwner } from '../models/InvoiceOwner';
import { InvoiceCommunity } from '../models/InvoiceCommunity copy';

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {

  private apiUrl = 'http://localhost:8080/api/commune';

  constructor(private http: HttpClient) { }

  getInvoicesByCommunity(communityId: number): Observable<InvoiceCommunity[]> {
    return this.http.get<InvoiceCommunity[]>(`${this.apiUrl}/invoices/dto/community/${communityId}`);
  }

  downloadCommunityInvoice(invoiceId: number): Observable<Blob> {
  return this.http.get(`${this.apiUrl}/invoices/${invoiceId}/pdf`, { responseType: 'blob' });
  }

  getInvoicesByOwner(ownerId: number): Observable<InvoiceOwner[]> {
    return this.http.get<InvoiceOwner[]>(`${this.apiUrl}/owner-invoices/owner/${ownerId}`);
  }
  
  downloadOwnerInvoice(invoiceId: number): Observable<Blob> {
  return this.http.get(`${this.apiUrl}/invoices/owner-invoice/${invoiceId}/pdf`, { responseType: 'blob' });
  }
 


}

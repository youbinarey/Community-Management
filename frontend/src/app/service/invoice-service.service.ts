import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Invoice } from '../models/Invoice';

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {

  private apiUrl = 'http://localhost:8080/api/commune/invoices';

  constructor(private http: HttpClient) { }

  getInvoicesByCommunity(communityId: number): Observable<Invoice[]> {
    return this.http.get<Invoice[]>(`${this.apiUrl}/dto/community/${communityId}`);
  }
}

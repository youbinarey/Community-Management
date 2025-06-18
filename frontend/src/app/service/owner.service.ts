import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Owner } from '../models/Owner';

@Injectable({
  providedIn: 'root'
})
export class OwnerService {

  private apiUrl1 = 'api/commune/owner';
  private apiUrl = 'http://localhost:8080/api/commune/owner';
  

  constructor(private http: HttpClient) { }

  getAllOwners(): Observable<Owner[]> {
    return this.http.get<Owner[]>(`${this.apiUrl}/dto`);
  }

  getPropertiesByOwner(ownerId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${ownerId}/properties`);
  }

  updateOwnerByID(ownerId: number, updateOwner: Owner): Observable<Owner> {
    return this.http.put<Owner>(`${this.apiUrl}/dto/${ownerId}`, updateOwner );
  }
    
   
}

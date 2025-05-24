// src/app/services/flat.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Flat } from '../models/Flat';

@Injectable({
  providedIn: 'root'
})
export class FlatService {


  // PUREBA PASAR DATOS 
  private apiUrl = 'http://localhost:8080/api/commune';
  

  constructor(private http: HttpClient) { }

  // Obtener todos los flats
  getAllFlats(): Observable<Flat[]> {
    return this.http.get<Flat[]>(`${this.apiUrl}/properties/flat`);
  }

  // Obtener flat por ID
  getFlatById(id: number): Observable<Flat> {
    return this.http.get<Flat>(`${this.apiUrl}/properties/flat/${id}`);
  }

  // Crear un nuevo flat
  createFlat(flat: Flat): Observable<Flat> {
    return this.http.post<Flat>(`${this.apiUrl}/properties/flat`, flat);
  }

  // Actualizar un flat
  updateFlat(id: number, flat: Flat): Observable<Flat> {
    return this.http.put<Flat>(`${this.apiUrl}/properties/flat/${id}`, flat);
  }

  // Eliminar un flat
  deleteFlat(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/properties/flat/${id}`);
  }

  getFlatsByCommunity(communityId: number): Observable<Flat[]> {
    return this.http.get<Flat[]>(`${this.apiUrl}/properties/flat/community/${communityId}`);
  }
}

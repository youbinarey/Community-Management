import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Parking } from '../models/Parking';

@Injectable({
  providedIn: 'root'
})
export class ParkingService {

  private apiUrl = 'api/commune'; 
  //private apiUrl = 'http://localhost:8080/api/commune'; 

  constructor(private http: HttpClient) { }

  getAllParkings(): Observable<Parking[]> {
      return this.http.get<Parking[]>(`${this.apiUrl}/properties/parking`);
    }
  

  getParkingsByCommunity(communityId: number) : Observable<Parking[]> {
    return this.http.get<Parking[]>(`${this.apiUrl}/properties/parking/community/${communityId}`);
  }

  createParking(parking: Parking): Observable<Parking> {
      return this.http.post<Parking>(`${this.apiUrl}/properties/create-parking`, parking);
    }
}

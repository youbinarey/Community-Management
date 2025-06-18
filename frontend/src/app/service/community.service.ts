import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Community } from '../models/Community';




@Injectable({
  providedIn: 'root'
})
  export class CommunityService {


  
    private apiUrl = 'http://localhost:8080/api/commune/communities/dto';
    private apiUrl1 = 'api/commune/communities/dto';


    communities: Community[];
    private _dato = 'Hola mundo'; // Ejemplo de dato privado

  constructor(private http: HttpClient) {
      this.communities = [];
   }

  getCommunities(): Observable<Community[]> {
    return this.http.get<Community[]>(this.apiUrl);
  }

  getCommunityById(id: number): Observable<Community> {
    return this.http.get<Community>(`${this.apiUrl}/${id}/detail`);
  }

  createCommunity(community: Community): Observable<Community>{
    return this.http.post<Community>(this.apiUrl, community);
  }

  get dato(): string {
    return this._dato; // Getter para acceder al dato privado
  }


  /**
   * Update a community by its ID.
   */
  updateCommunity(id: number, updateCommunity: Community): Observable<Community>{
    return this.http.put<Community>(`${this.apiUrl}/${id}`, updateCommunity);
  };
}



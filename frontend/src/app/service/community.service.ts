import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Community } from '../models/Community';




@Injectable({
  providedIn: 'root'
})
  export class CommunityService {
    private apiUrl = 'http://localhost:8080/api/commune/communities/dto';

    communities: Community[];
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


}



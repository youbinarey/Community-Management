import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Community } from '../models/Community';




@Injectable({
  providedIn: 'root'
})
  /**
   * Service for managing community-related operations.
   * 
   * This service provides methods to interact with the backend API for performing CRUD operations
   * on Community entities, such as retrieving all communities, fetching a community by its ID,
   * creating a new community, and updating an existing community.
   */
  export class CommunityService {
  // Localhost
    //private apiUrl = 'http://localhost:8080/api/commune/communities/dto';
    //docker file
    private apiUrl = 'api/commune/communities/dto';
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

  /**
   * Update a community by its ID.
   */
  updateCommunity(id: number, updateCommunity: Community): Observable<Community>{
    return this.http.put<Community>(`${this.apiUrl}/${id}`, updateCommunity);
  };
}



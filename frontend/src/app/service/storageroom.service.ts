import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageRoom } from '../models/StorageRoom';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StorageroomService {
  private apiUrl = 'http://localhost:8080/api/commune'; 

  constructor(private http: HttpClient) { }

  
    getAllStorageRooms(): Observable<StorageRoom[]> {
      return this.http.get<StorageRoom[]>(`${this.apiUrl}/properties/storage-room`);
    }

  getStorageRoomsByCommunity(communityId: number) : Observable<StorageRoom[]> {
    return this.http.get<StorageRoom[]>(`${this.apiUrl}/properties/storageroom/community/${communityId}`);
  }

  createStorageRoom(storageRoom: StorageRoom): Observable<StorageRoom> {
    return this.http.post<StorageRoom>(`${this.apiUrl}/properties/create-storage-room`, storageRoom);
  }

}

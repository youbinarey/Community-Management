import { NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StorageroomService } from '../../../service/storageroom.service';
import { StorageRoom } from '../../../models/StorageRoom';


@Component({
  selector: 'app-storageroom',
  imports: [NgFor],
  templateUrl: './storageroom.component.html',
  styleUrl: './storageroom.component.scss'
})
/**
 * Component responsible for displaying and managing storage rooms associated with a specific community.
 *
 * This component retrieves the community ID from the route parameters and fetches the corresponding storage rooms
 * using the `StorageroomService`. It also attempts to retrieve the community name from the router's navigation state
 * or from the fetched storage room data.
 */
export class StorageroomComponent implements OnInit {
  storageRooms: StorageRoom[] = [];
  communityId: number | undefined;
  communityName: string | undefined;
  constructor(private storageroomService: StorageroomService, private route: ActivatedRoute, private router: Router) { }
  ngOnInit(): void {

    this.route.paramMap.subscribe(params => {
      const id = params.get('communityId');
      if (id) {
        this.communityId = +id;
        this.getStorageRoomsByCommunity(this.communityId);
      }
    });

    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.communityName = navigation.extras.state['communityName'];
      console.log(this.communityName);
    }
  }

  getStorageRoomsByCommunity(communityId: number) {
    this.storageroomService.getStorageRoomsByCommunity(communityId).subscribe({
      next: (data) => {
        this.storageRooms = data;
        this.communityId = communityId;
        this.communityName = data[0]?.communityName || 'Comunidad no encontrada';
        console.log('Storage Rooms recibidos:', this.storageRooms);
      },
      error: (error) => {
        console.error('Error al obtener los parkings:', error);
      }
    });
  }

}

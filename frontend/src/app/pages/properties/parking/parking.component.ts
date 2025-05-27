import { NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Parking } from '../../../models/Parking';
import { ParkingService } from '../../../service/parking.service';
import { ActivatedRoute, Route, Router } from '@angular/router';

@Component({
  selector: 'app-parking',
  imports: [NgFor],
  templateUrl: './parking.component.html',
  styleUrl: './parking.component.scss'
})
export class ParkingComponent implements OnInit {
  parkings: Parking[] = [];
  communityId: number | undefined;
  communityName: string | undefined;
  constructor(private parkingService: ParkingService, private route: ActivatedRoute, private router: Router){}


  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('communityId');
      if (id) {
        this.communityId = +id; 
        this.getParkingsByCommunity(this.communityId);
      }
    });

     const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.communityName = navigation.extras.state['communityName'];
      console.log(this.communityName); 
    }
  }

  getParkingsByCommunity(communityId: number): void {
    this.parkingService.getParkingsByCommunity(communityId).subscribe({
      next: (data) => {
        this.parkings = data;
        this.communityId = communityId;
        this.communityName =data[0]?.communityName || 'Comunidad no encontrada';
        console.log('Parkings recibidos:', this.parkings);
      },
      error: (error) => {
        console.error('Error al obtener los parkings:', error)
      }
  });
  }
  
}



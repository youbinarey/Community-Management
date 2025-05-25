import { Component, OnInit } from '@angular/core';
import { Flat } from '../../../models/Flat';
import { FlatService } from '../../../service/flat.service';
import { NgFor } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';



@Component({
  selector: 'app-flat',
  templateUrl: './flat.component.html',
  imports: [NgFor,],
  styleUrls: ['./flat.component.scss']
})
export class FlatComponent implements OnInit {
  flats: Flat[] = [];
  communityId: number | undefined;
  communityName: string | undefined ;
  constructor(private flatService: FlatService, private route: ActivatedRoute, private router: Router) { }
  ngOnInit(): void {
    
    this.route.paramMap.subscribe(params => {
      const id = params.get('communityId');
      if (id) {
        this.communityId = +id; 
        this.getFlatsByCommunity(this.communityId);
      }
    });

     const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.communityName = navigation.extras.state['communityName'];
      console.log(this.communityName);  
    }
    
  }


  getFlatsByCommunity(communityId: number): void {
    this.flatService.getFlatsByCommunity(communityId).subscribe({
      next: (data) => {
        this.flats = data;
        this.communityId = communityId;
        this.communityName =data[0]?.communityName || 'Comunidad no encontrada';
        console.log('Flats recibidos:', this.flats);
      },
      error: (error) => {
        console.error('Error al obtener los flats:', error)
      }
  });
  }




}

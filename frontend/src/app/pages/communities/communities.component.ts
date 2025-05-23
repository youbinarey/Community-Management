import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { CommunityService } from '../../service/community.service';
import { Community } from '../../models/community';



@Component({
  selector: 'app-communities',
  imports: [NgFor, NgIf, FormsModule],
  templateUrl: './communities.component.html',
  styleUrl: './communities.component.scss'
})


export class CommunitiesComponent implements OnInit {

  communities: Community[] = [];
  filtro = '';
  selectedCommunity?: Community;
  showProperties: boolean = false;

  constructor(private communityService: CommunityService) { }

  ngOnInit(): void {
    this.communityService.getCommunities().subscribe(data => {
      console.log('Datos recibidos:', data); // 👈 esto te dirá si llegan datos
      this.communities = data;
    });
  }


  getFilteredCommunities(): Community[] {
    const filtered = this.communities.filter(c =>
      c.address.toLowerCase().includes(this.filtro.toLowerCase())
    );
    console.log('Filtradas:', filtered);
    return filtered;
  }


  verComunidad(id: number): void {
  this.communityService.getCommunityById(id).subscribe({
    next: (data) => {
      this.selectedCommunity = data;
      console.log('Comunidad seleccionada:', this.selectedCommunity);
    },
    error: (e) => console.error('Error al cargar comunidad', e)
  });
}

  toggleProperties(): void {
    this.showProperties = !this.showProperties;
  }



  // getFilteredCommunities() {
  //   return this.comunidades.filter(c =>
  //     c.address.toLowerCase().includes(this.filtro.toLowerCase())
  //   );
  // }

  // nueva = {
  //   address: '',
  //   postalCode: '',
  //   elevator: true,
  //   numFloors: 0,
  //   numparkings: 0,
  //   numStorageRooms: 0
  // };
  // crearComunidad() {
  //   this.comunidades.push({ ...this.nueva });
  //   this.nueva = {
  //     address: '',
  //     postalCode: '',
  //     elevator: true,
  //     numFloors: 0,
  //     numparkings: 0,
  //     numStorageRooms: 0
  //   };
  //   const modal = document.getElementById('modalNuevaComunidad');
  //   if (modal) {
  //     const instanciaModal = bootstrap.Modal.getInstance(modal) || new bootstrap.Modal(modal);
  //     instanciaModal.hide();
  //   }
  // }


}
import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommunityService } from '../../service/community.service';
import { Community } from '../../models/Community';
import { Modal } from 'bootstrap';



@Component({
  selector: 'app-communities',
  imports: [NgFor, NgIf, FormsModule],
  templateUrl: './communities.component.html',
  styleUrls: ['./communities.component.scss']
})


export class CommunitiesComponent implements OnInit {

  communities: Community[] = [];
  filtro = '';
  selectedCommunity?: Community;
  showProperties: boolean = false;
  isLoading: boolean = false;
  

  constructor(private communityService: CommunityService) { }

  ngOnInit(): void {
    this.getCommunities();
  }


  private getCommunities() {
    this.communityService.getCommunities().subscribe(data => {
      console.log('Datos recibidos:', data);
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
      this.openModal('modalCommunityDetails'); 
    },
    error: (e) => console.error('Error al cargar comunidad', e)
  });
}

  toggleProperties(): void {
    this.showProperties = !this.showProperties;
  }



// Crear nueva comuniad
newCommunity: Community = {
  id: 0,
  address: '',
  postalCode: '',
  propertiesCount: 0,
  elevator: false,
  numFloors : 0,
  numparkings : 0,
  numStorageRooms : 0,
  reducedMobilityAccess : false,
  bankAccountNumber:''
}

//crear una nueva comunidad
  addCommunity(): void {
    this.isLoading = true;
    this.communityService.createCommunity(this.newCommunity).subscribe({
      next: (data) => {
        console.log('Comunidad creada:', data);

        if(data){
        this.communities.push(data); // Añadir la nueva comunidad a la lista
        this.cerrarModal('modalNewCommunity'); // Cerrar el modal después de crear la comunidad
        
        } else {
          console.error('Error: La comunidad no se creó correctamente');
        }

        this.isLoading = false; 
      },
      error: (e) => {console.error('Error al crear comunidad', e)
      this.isLoading = false; // Asegurarse de que isLoading se restablezca incluso si hay un error}
    }
  });
  }


  // Método para abrir el modal
openModal(modalId: string):void {
  const modal = document.getElementById(modalId);
  if (!modal) return; // Salir si el modal no se encuentra

  const modalInstance = new Modal(modal); // Crear una nueva instancia del modal de Bootstrap
  modalInstance.show(); // Mostrar el modal
}
  // Método para cerrar el modal
cerrarModal(modalId: string): void {
  const modal = document.getElementById(modalId);
  if (!modal) return; // Salir si el modal no se encuentra

  const modalInstance = Modal.getInstance(modal); // Obtener la instancia del modal
  if (modalInstance) {
    modalInstance.hide(); // Cerrar el modal
  }
}

}
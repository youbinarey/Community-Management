import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommunityService } from '../../service/community.service';
import { Community } from '../../models/Community';
import { Modal } from 'bootstrap';
import { Router } from '@angular/router';
import { StorageRoom } from '../../models/StorageRoom';
import { Parking } from '../../models/Parking';
import { Flat } from '../../models/Flat';
import { FlatService } from '../../service/flat.service';
import { StorageroomService } from '../../service/storageroom.service';
import { ParkingService } from '../../service/parking.service';



/**
 * Component for managing and displaying communities.
 *
 * Handles CRUD operations for communities, flats, parkings, and storage rooms.
 * Provides filtering, modal management, and navigation to property and invoice details.
 */
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
  isLoading: boolean = false;
  isEditMode: boolean = false;

  newFlat: Flat = {} as Flat;
  newParking: Parking = {} as Parking;
  newStorage: StorageRoom = {} as StorageRoom;



  constructor(private communityService: CommunityService,
    private flatService: FlatService,
    private storageroomService: StorageroomService,
    private parkingService: ParkingService,
    private router: Router) { }

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





  // Crear nueva comuniad
  newCommunity: Community = {
    id: 0,
    address: '',
    postalCode: '',
    propertiesCount: 0,
    elevator: false,
    numFlats: 0,
    numparkings: 0,
    numStorageRooms: 0,
    reducedMobilityAccess: false,
    bankAccountNumber: ''
  }

  //crear una nueva comunidad
  addCommunity(): void {
    this.isLoading = true;
    this.communityService.createCommunity(this.newCommunity).subscribe({
      next: (data) => {
        console.log('Comunidad creada:', data);

        if (data) {
          this.communities.push(data); // Añadir la nueva comunidad a la lista
          this.cerrarModal('modalNewCommunity'); // Cerrar el modal después de crear la comunidad

        } else {
          console.error('Error: La comunidad no se creó correctamente');
        }

        this.isLoading = false;
      },
      error: (e) => {
        console.error('Error al crear comunidad', e)
        this.isLoading = false; // Asegurarse de que isLoading se restablezca incluso si hay un error}
      }
    });
  }


  // Método para abrir el modal
  openModal(modalId: string): void {
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


  //VER PROPIEDADES
  verMasDetalles(tipo: string) {
      this.cerrarModal('modalCommunityDetails');

    if (tipo === 'flats') {
      this.router.navigate(['/properties/flat', this.selectedCommunity?.id], { state: { communityName: this.selectedCommunity?.address } });
    } else if (tipo === 'parkings') {
      this.router.navigate(['/properties/parking', this.selectedCommunity?.id], { state: { communityName: this.selectedCommunity?.address } });
    } else if (tipo === 'storageroom') {
      this.router.navigate(['/properties/storageroom', this.selectedCommunity?.id], { state: { communityName: this.selectedCommunity?.address } });
    }
  }


  saveChanges(): void {
    if (this.selectedCommunity && this.selectedCommunity.id) {
      this.communityService.updateCommunity(this.selectedCommunity.id, this.selectedCommunity).subscribe({
        next: (updatedCommunity) => {
          console.log('Comunidad actualizada:', updatedCommunity);
          this.selectedCommunity = updatedCommunity;
          this.isEditMode = false;

        },
        error: (e) => {
          console.error('Error al actualizar la comunidad', e);
        }
      });
    } else {
      console.error('No se puede actualizar la comunidad: ID no disponible');
    }
  }


  openModalFlat() {
    this.newFlat = {
      id: 0,
      cadastralReference: '',
      squareMeters: 0,
      floorNumber: 0,
      letter: '',
      roomCount: 0,
      bathroomCount: 0,
      communityId: this.selectedCommunity?.id!,
      communityName: this.selectedCommunity?.address,
      ownerDni: '',
    } as Flat;
    this.openModal('modalFlat');
  }

  openModalParking() {
    this.newParking = {
      id: 0,
      squareMeters: 0,
      parkingNumber: '',
      cadastralReference: '',
      num: 0,
      communityId: this.selectedCommunity?.id!,
      communityName: this.selectedCommunity?.address,
      ownerDni: ''
    } as Parking;
    this.openModal('modalParking');
  }

  openModalStorage() {
    this.newStorage = {
      id: 0,
      squareMeters: 0,
      storageNumber: 0,
      cadastralReference: '',
      num: 0,
      communityId: this.selectedCommunity?.id!,
      communityName: this.selectedCommunity?.address,
      ownerDni: '',
    } as StorageRoom;
    this.openModal('modalStorage');
  }

  saveFlat() {
    console.log('Guardando Flat:', this.newFlat);
    this.flatService.createFlat(this.newFlat).subscribe({
      next: (flat) => {
        console.log('Flat guardado:', flat);
        this.cerrarModal('modalFlat');

        if (this.selectedCommunity && this.selectedCommunity.id) {
          this.communityService.getCommunityById(this.selectedCommunity.id).subscribe({
            next: (community) => {
              this.selectedCommunity = community; // Actualizar la comunidad seleccionada
              console.log('Comunidad actualizada:', this.selectedCommunity);
            },
            error: (error) => {
              console.error('Error al guardar Flat:', error)
            }
          });
        }
        this.getCommunities();
      },
      error: (error) => {
        console.error('Error al guardar Flat:', error);
      }
    });
  }

  saveParking() {
    console.log('Guardando Parking:', this.newParking);
    this.parkingService.createParking(this.newParking).subscribe({
      next: (parking) => {
        console.log('Parking guardado:', parking);
        this.cerrarModal('modalParking');

        if (this.selectedCommunity && this.selectedCommunity.id) {
          this.communityService.getCommunityById(this.selectedCommunity.id).subscribe({
            next: (community) => {
              this.selectedCommunity = community; // Actualizar la comunidad seleccionada
              console.log('Comunidad actualizada:', this.selectedCommunity);
            },
            error: (error) => {
              console.error('Error al actualizar comunidad:', error);
            }
          });
        }
        this.getCommunities();
      },
      error: (error) => {
        console.error('Error al guardar Parking:', error);
      }
    });
  }



  saveStorage() {
    console.log('Guardando StorageRoom:', this.newStorage);
    this.storageroomService.createStorageRoom(this.newStorage).subscribe({
      next: (storageRoom) => {
        console.log('StorageRoom guardado:', storageRoom);
        this.cerrarModal('modalStorageRoom');

        if (this.selectedCommunity && this.selectedCommunity.id) {
          this.communityService.getCommunityById(this.selectedCommunity.id).subscribe({
            next: (community) => {
              this.selectedCommunity = community; // Actualizar la comunidad seleccionada
              console.log('Comunidad actualizada:', this.selectedCommunity);
            },
            error: (error) => {
              console.error('Error al actualizar comunidad:', error);
            }
          });
        }
        this.getCommunities();
      },
      error: (error) => {
        console.error('Error al guardar StorageRoom:', error);
      }
    });
  }


  // RECIBOS
  openInvoice(communityId: number) {
    this.cerrarModal('modalCommunityDetails');
    this.router.navigate(['invoices',communityId])
  }
}

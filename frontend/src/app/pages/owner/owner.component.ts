import { Component, OnInit } from '@angular/core';
import { Owner } from '../../models/Owner';
import { OwnerService } from '../../service/owner.service';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { Modal } from 'bootstrap'; // Import Bootstrap Modal
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-owner',
  imports: [NgIf, NgFor, CommonModule, FormsModule],
  templateUrl: './owner.component.html',
  styleUrl: './owner.component.scss'
})
/**
 * Component for managing and displaying property owners and their related data.
 *
 * This component allows users to:
 * - View a list of all property owners.
 * - Search and filter owners by name, surname, email, phone, or DNI.
 * - View properties associated with a selected owner in a modal dialog.
 * - Edit and save changes to owner details.
 * - Navigate to the invoices page for a specific owner.
 */
export class OwnerComponent implements OnInit {


  owners: Owner[] = [];
  ownerProperties: any[] = [];
  selectedOwner: Owner | null = null;
  searchTerm: string = '';

  constructor(private ownerService: OwnerService,private router: Router) { }

  ngOnInit(): void {
    this.ownerService.getAllOwners().subscribe((data) =>{
      this.owners = data;
    });   
  }

  viewOwnerProperties(ownerId: number): void {
    this.selectedOwner = this.owners.find(owner => owner.id === ownerId) || null;
    this.ownerService.getPropertiesByOwner(ownerId).subscribe({
      next: (data: any) => {
        this.ownerProperties = data;
        this.openPropertiesModal();
      },
      error: (error: any) => {
        console.error('Error fetching properties for owner:', error);
      }
    });
  }


  openPropertiesModal(): void {
    const modalElement = document.getElementById('propertiesModal');
    const modal = new Modal(modalElement!);
    modal.show(); 
  
  }

  closePropertiesModal(): void{
    const modalElement = document.getElementById('propertiesModal');
    const modal = Modal.getInstance(modalElement!);
    modal?.hide();
  }

  saveChanges(owner: Owner): void {
    this.ownerService.updateOwnerByID(owner.id, owner).subscribe({
      next: (updatedOwner) => {
        Object.assign(owner, updatedOwner); // Actualiza los datos en la lista
        owner.isEditMode = false; // Vuelve a modo visualización
        console.log('Propietario actualizado:', updatedOwner);
      },
      error: (error) => console.error('Error al actualizar propietario:', error)
    });
  }


   filteredOwners(): Owner[] {
    if (!this.searchTerm) {
      return this.owners;
    }

    const term = this.searchTerm.toLowerCase();
    return this.owners.filter(owner =>
      (owner.name?.toLowerCase().includes(term)) ||
      (owner.surname?.toLowerCase().includes(term)) ||
      (owner.email?.toLowerCase().includes(term)) ||
      (owner.phone?.toLowerCase().includes(term)) ||
      (owner.dni?.toLowerCase().includes(term))
    );
  }


 viewOwnerInvoices(ownerId: number) {
  this.router.navigate(['/invoices/owner', ownerId]);
}
}

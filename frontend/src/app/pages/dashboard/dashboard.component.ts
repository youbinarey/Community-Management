import { Component, OnInit } from '@angular/core';
import { CommunityService } from '../../service/community.service';
import { OwnerService } from '../../service/owner.service';
import { FlatService } from '../../service/flat.service';
import { ParkingService } from '../../service/parking.service';
import { StorageroomService } from '../../service/storageroom.service';
import { InvoiceService } from '../../service/invoice-service.service';
import { CommonModule } from '@angular/common';
import {  MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule, MatCardModule, MatIconModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
/**
 * DashboardComponent is responsible for displaying a summary dashboard with key statistics
 * such as the number of communities, owners, flats, parkings, storerooms, and invoices.
 */
export class DashboardComponent implements OnInit{
  
  summaryCards = [
    { title: 'Comunidades', count: 0, icon:'location_city', route: '/communities' },
    { title: 'Propietarios', count: 0,icon: 'account_circle' },
    { title: 'Viviendas', count: 0,icon: 'home' },
    { title: 'Garajes', count: 0,icon: 'local_parking' },
    { title: 'Trasteros', count: 0,icon: 'storage' },
    { title: 'Facturas', count: 0,icon: 'receipt' },

  ];
   constructor(
    private router: Router,
    private communityService: CommunityService,
    private ownerService: OwnerService,
    private flatService: FlatService,
    private parkingService: ParkingService,
    private storageroomService: StorageroomService,
    private invoiceService: InvoiceService
  ) {}
  goTo(path: string){
    this.router.navigate([path]);
  }

  ngOnInit(): void {
    this.loadDashboardData();
  }
  /**
   * Loads the initial data for the dashboard by fetching counts from various services.
   * Each service call updates the corresponding summary card with the count of items.
   */
  loadDashboardData() {
    this.communityService.getCommunities().subscribe(data => this.summaryCards[0].count = data.length);
    this.ownerService.getAllOwners().subscribe(data => this.summaryCards[1].count = data.length);
    this.flatService.getAllFlats().subscribe(data => this.summaryCards[2].count = data.length);
    this.parkingService.getAllParkings().subscribe(data => this.summaryCards[3].count = data.length);
    this.storageroomService.getAllStorageRooms().subscribe(data => this.summaryCards[4].count = data.length);
    this.invoiceService.getAllInvoices().subscribe(data => this.summaryCards[5].count = data.length);
  }
  
  

}

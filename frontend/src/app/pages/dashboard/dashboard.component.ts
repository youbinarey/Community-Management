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
export class DashboardComponent implements OnInit{
  
  summaryCards = [
    { title: 'Comunidades', count: 0, icon:'location_city', route: '/communities' },
    { title: 'Propietarios', count: 0 },
    { title: 'Viviendas', count: 0 },
    { title: 'Garajes', count: 0 },
    { title: 'Facturas', count: 0 },

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
  loadDashboardData() {
    this.communityService.getCommunities().subscribe(data => this.summaryCards[0].count = data.length);
    this.ownerService.getAllOwners().subscribe(data => this.summaryCards[1].count = data.length);
    // this.flatService.getAllFlats.subscribe(data => this.summaryCards[2].count = data.length);
    // this.parkingService.getAll().subscribe(data => this.summaryCards[3].count = data.length);
    // this.storageroomService.getAll().subscribe(data => this.summaryCards[4].count = data.length);
    // this.invoiceService.getAll().subscribe(data => this.summaryCards[5].count = data.length);
  }
  
  

}

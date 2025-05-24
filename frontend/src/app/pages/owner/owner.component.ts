import { Component, OnInit } from '@angular/core';
import { Owner } from '../../models/Owner';
import { OwnerService } from '../../service/owner.service';
import { CommonModule, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-owner',
  imports: [NgIf, NgFor, CommonModule],
  templateUrl: './owner.component.html',
  styleUrl: './owner.component.scss'
})
export class OwnerComponent implements OnInit {
viewOwnerDetails(_t12: Owner) {
throw new Error('Method not implemented.');
}
  owners: Owner[] = [];

  constructor(private ownerService: OwnerService) { }

  ngOnInit(): void {
    this.ownerService.getAllOwners().subscribe((data) =>{
      this.owners = data;
    });   
  }

  // Additional methods and properties can be added as needed

}

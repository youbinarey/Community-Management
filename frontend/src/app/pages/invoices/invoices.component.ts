import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Invoice } from '../../models/Invoice';
import { InvoiceService } from '../../service/invoice-service.service';
import { CommonModule, NgFor } from '@angular/common';

@Component({
  selector: 'app-invoices',
  templateUrl: './invoices.component.html',
  styleUrls: ['./invoices.component.scss'],
  imports: [NgFor, CommonModule],
})
export class InvoicesComponent implements OnInit {

  communityId!: number;
  invoices: Invoice[] = [];

  constructor(
    private route: ActivatedRoute,
    private invoiceService: InvoiceService,
  ) {}

  ngOnInit() {
    this.communityId = Number(this.route.snapshot.paramMap.get('communityId'));
    this.loadInvoices();
  }

  loadInvoices() {
    this.invoiceService.getInvoicesByCommunity(this.communityId).subscribe(data => {
      this.invoices = data;
    });
  }

 // TODO DESCARGAR PDF
}

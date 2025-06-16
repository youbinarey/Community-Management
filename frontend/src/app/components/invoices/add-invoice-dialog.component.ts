import { Component, Inject } from '@angular/core';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { InvoiceCommunity } from '../../models/InvoiceCommunity';

@Component({
  selector: 'app-add-invoice-dialog',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './add-invoice-dialog.component.html',
  styleUrls: ['./add-invoice-dialog.component.scss']
})
export class AddInvoiceDialogComponent {
  form;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<AddInvoiceDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { communityId: number }
  ) {
    this.form = this.fb.group({
      date: ['', Validators.required],
      electricity: [0, Validators.required],
      water: [0, Validators.required],
      trash: [0, Validators.required],
      elevator: [0, Validators.required],
      maintenance: [0, Validators.required]
    });
  }

  save() {
    if (this.form.valid) {
      const invoice: InvoiceCommunity = {
        id: 0,
        communityId: this.data.communityId,
        communityName: '',
        ...this.form.value
      } as InvoiceCommunity;

      this.dialogRef.close(invoice);
    }
  }

  cancel() {
    this.dialogRef.close();
  }
}

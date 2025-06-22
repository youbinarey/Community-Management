import { Component, Inject } from '@angular/core';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { InvoiceCommunity } from '../../models/InvoiceCommunity';

/**
 * Component for displaying a dialog to add a new invoice for a community.
 *
 * This component provides a reactive form for entering invoice details such as date,
 * electricity, water, trash, elevator, and maintenance costs. Upon submission,
 * it emits the created `InvoiceCommunity` object and closes the dialog.
 */
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

  /**
   * Initializes a new instance of the AddInvoiceDialogComponent.
   * 
   * @param fb - The FormBuilder service used to create the invoice form group.
   * @param dialogRef - Reference to the dialog opened for adding an invoice.
   * @param data - Data injected into the dialog, containing the community ID.
   * 
   * Sets up a reactive form with controls for date, electricity, water, trash, elevator, and maintenance,
   * each with required validators.
   */
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

  /**
   * Saves the invoice form data if the form is valid.
   * Constructs an `InvoiceCommunity` object using the form values and additional data,
   * then closes the dialog, passing the created invoice object as the result.
   */
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

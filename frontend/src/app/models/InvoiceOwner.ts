/**
 * Represents the owner of an invoice with associated details.
 *
 * @property id - Unique identifier for the invoice owner entry.
 * @property date - Date associated with the invoice owner record (ISO string).
 * @property amount - Amount related to the invoice for this owner.
 * @property invoiceId - Identifier of the related invoice.
 * @property ownerName - First name of the invoice owner.
 * @property ownerSurname - Surname of the invoice owner.
 * @property ownerBankAccount - Bank account number of the owner.
 * @property coefficient - Coefficient or share associated with the owner.
 * @property communityName - Name of the community the owner belongs to.
 * @property email - Email address of the invoice owner.
 */
export interface InvoiceOwner {
  id: number;
  date: string;
  amount: number;
  invoiceId: number;
  ownerName: string;
  ownerSurname: string;
  ownerBankAccount: string;
  // properties?: PropertyDTO[];
  coefficient: number;
  communityName: string;
  email: string;
}

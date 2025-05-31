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
}

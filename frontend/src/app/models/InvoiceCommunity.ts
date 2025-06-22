/**
 * Represents an invoice for a community, detailing various expenses.
 *
 * @property id - Unique identifier for the invoice.
 * @property date - Date when the invoice was issued (ISO string).
 * @property electricity - Amount charged for electricity.
 * @property water - Amount charged for water.
 * @property trash - Amount charged for trash collection.
 * @property elevator - Amount charged for elevator maintenance.
 * @property maintenance - Amount charged for general maintenance.
 * @property communityId - Identifier of the community associated with the invoice.
 * @property communityName - Name of the community associated with the invoice.
 */
export interface InvoiceCommunity {
  id: number;
  date: string;
  electricity: number;
  water: number;
  trash: number;
  elevator: number;
  maintenance: number;
  communityId: number;
  communityName: string;
}

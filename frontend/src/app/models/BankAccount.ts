/**
 * Represents a bank account with its basic details.
 *
 * @property id - Unique identifier for the bank account.
 * @property accountNumber - The account number associated with the bank account.
 * @property bankName - The name of the bank where the account is held.
 * @property balance - The current balance of the bank account.
 */
export interface BankAccount {
  id: number;
  accountNumber: string;
  bankName: string;
  balance: number;
}

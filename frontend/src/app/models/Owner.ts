/**
 * Represents an owner entity with personal and contact information.
 *
 * @property isEditMode - Indicates if the owner is currently in edit mode.
 * @property id - Unique identifier for the owner.
 * @property dni - National identification number of the owner.
 * @property name - First name of the owner.
 * @property surname - Last name of the owner.
 * @property email - Email address of the owner.
 * @property phone - Phone number of the owner.
 * @property birthDate - Birth date of the owner.
 * @property bankAccountNumber - Bank account number associated with the owner.
 * @property propertiesCount - Number of properties owned.
 */
export interface Owner {
isEditMode: any;
  id: number;
  dni: string;
  name: string;
  surname: string;
  email: string;
  phone: string;
  birthDate: Date;
  bankAccountNumber: string;
  propertiesCount: number;
}

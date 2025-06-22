
import { Property } from "./Property";


/**
 * Represents a community entity with its basic information and optional features.
 *
 * @property id - Unique identifier for the community.
 * @property address - Physical address of the community.
 * @property postalCode - Postal code of the community's location.
 * @property elevator - Indicates if the community has an elevator (optional).
 * @property numFlats - Number of flats in the community (optional).
 * @property numparkings - Number of parking spaces in the community (optional).
 * @property numStorageRooms - Number of storage rooms in the community (optional).
 * @property reducedMobilityAccess - Indicates if the community has access for reduced mobility (optional).
 * @property bankAccountNumber - Bank account number associated with the community (optional).
 * @property properties - List of properties belonging to the community (optional).
 * @property propertiesCount - Total number of properties in the community (optional).
 */
export interface Community {
  id: number;
  address: string;
  postalCode: string;
  elevator?: boolean;
  numFlats?: number;
  numparkings?: number;
  numStorageRooms?: number;
  reducedMobilityAccess?: boolean;
  bankAccountNumber?: string;
  properties?: Property[];
  propertiesCount?: number;
}

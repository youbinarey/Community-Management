
import { Property } from "./Property";


export interface Community {
  id: number;
  address: string;
  postalCode: string;
  elevator?: boolean;
  numFloors?: number;
  numparkings?: number;
  numStorageRooms?: number;
  reducedMobilityAccess?: boolean;
  bankAccountNumber?: string;
  properties?: Property[];
  propertiesCount?: number;
}

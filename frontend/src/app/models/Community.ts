
import { Property } from "./Property";


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

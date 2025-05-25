// src/app/models/flat.model.ts
export interface Flat {
  id: number;
  cadastralReference: string;
  squareMeters: number;
  floorNumber: number;
  letter: string;
  roomCount: number;
  bathroomCount: number;
  communityName: string;
  ownerName?: string;
  ownerDni?: string; 
}

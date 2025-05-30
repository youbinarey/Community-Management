export interface Parking {
    id: number;
    cadastralReference: string;
    squareMeters: number;
    num: number;
  coefficient?: number;
    communityName: string
    ownerName?: string;
    ownerDni?: string;
}
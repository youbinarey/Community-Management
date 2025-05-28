export interface StorageRoom {
    id: number;
    cadastralReference: string;
    squareMeters: number;
    storageNumber: number;
    coefficient?: number;
    communityName: string;
    ownerName?: string;
    ownerDni?: string;
}
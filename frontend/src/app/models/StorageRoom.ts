/**
 * Represents a storage room within a community property.
 *
 * @property id - Unique identifier for the storage room.
 * @property cadastralReference - Official cadastral reference for the storage room.
 * @property squareMeters - Area of the storage room in square meters.
 * @property storageNumber - Number assigned to the storage room.
 * @property coefficient - (Optional) Coefficient representing the storage room's share in the community.
 * @property communityName - Name of the community where the storage room is located.
 * @property ownerName - (Optional) Name of the storage room's owner.
 * @property ownerDni - (Optional) DNI (identification number) of the storage room's owner.
 */
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
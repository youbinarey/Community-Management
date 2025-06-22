/**
 * Represents a flat (apartment) within a building or community.
 *
 * @property id - Unique identifier for the flat.
 * @property cadastralReference - Official cadastral reference for property identification.
 * @property squareMeters - Total area of the flat in square meters.
 * @property floorNumber - The floor on which the flat is located.
 * @property letter - Letter designation of the flat (e.g., 'A', 'B').
 * @property roomCount - Number of rooms in the flat.
 * @property bathroomCount - Number of bathrooms in the flat.
 * @property communityName - Name of the community or building the flat belongs to.
 * @property ownerName - (Optional) Name of the flat's owner.
 * @property ownerDni - (Optional) DNI (identification number) of the owner.
 * @property coefficient - (Optional) Coefficient representing the flat's share in the community.
 */
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
  coefficient?: number;
}

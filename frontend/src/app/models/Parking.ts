/**
 * Represents a parking space within a community.
 *
 * @property id - Unique identifier for the parking space.
 * @property cadastralReference - Cadastral reference code for the parking space.
 * @property squareMeters - Area of the parking space in square meters.
 * @property num - Number assigned to the parking space.
 * @property coefficient - (Optional) Coefficient value associated with the parking space.
 * @property communityName - Name of the community where the parking space is located.
 * @property ownerName - (Optional) Name of the owner of the parking space.
 * @property ownerDni - (Optional) DNI (identification number) of the owner.
 */
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
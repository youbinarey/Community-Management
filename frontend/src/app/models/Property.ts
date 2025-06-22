/**
 * Represents a property with cadastral and physical details.
 *
 * @property id - Unique identifier for the property.
 * @property cadastralReference - The cadastral reference code of the property.
 * @property squareMeters - The total area of the property in square meters.
 * @property num - (Optional) The property number or unit number.
 * @property storageNumber - (Optional) The storage unit number associated with the property.
 * @property coefficient - (Optional) The coefficient value related to the property.
 */
export interface Property {
  id: number;
  cadastralReference: string;
  squareMeters: number;
  num?: number;
  storageNumber?: number;
  coefficient?: number;
}

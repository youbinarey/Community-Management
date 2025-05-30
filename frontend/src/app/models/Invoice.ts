export interface Invoice {
  id: number;
  date: string;
  electricity: number;
  water: number;
  trash: number;
  elevator: number;
  maintenance: number;
  communityId: number;
  communityName: string;
}

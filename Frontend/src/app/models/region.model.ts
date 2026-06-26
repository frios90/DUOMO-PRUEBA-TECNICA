import { Commune } from './commune.model';

export interface Region {
  id: string;
  name: string;
  communes: Commune[];
}
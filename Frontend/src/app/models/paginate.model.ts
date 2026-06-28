import { Person } from './person.model';

export interface Paginate {
  content: Person[];
  pageNumber: number;
  pageSize: number;
  totalElements: number;
  totalPages: number;
  last: boolean;
  first: boolean;
}
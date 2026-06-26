export interface Person {
  id?: string;
  name: string;
  lastName: string;
  email: string;
  age: number;
  regionId: string;
  communeId: string;
  regionName?: string;
  communeName?: string;
}

export interface Region {
  id: string;
  name: string;
  communes: Commune[];
}

export interface Commune {
  id: string;
  name: string;
}

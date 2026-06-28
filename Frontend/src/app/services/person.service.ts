import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Paginate } from '../models/paginate.model';
import { Person } from '../models/person.model';
import { Region } from '../models/region.model';
import { Commune } from '../models/commune.model';

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  private apiUrl = 'http://localhost:8080/api';
  // private apiUrl = 'http://localhost:9090/api';

  constructor(private http: HttpClient) {}

  getPaginatePeople(page: number = 0, size: number = 10): Observable<Paginate> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<Paginate>(`${this.apiUrl}/people/paginate`, { params });
  }

  getPeople(): Observable<Person[]> {
    return this.http.get<Person[]>(`${this.apiUrl}/people`);
  }

  getPerson(id: string): Observable<Person> {
    return this.http.get<Person>(`${this.apiUrl}/people/${id}`);
  }

  createPerson(person: Person): Observable<Person> {
    return this.http.post<Person>(`${this.apiUrl}/people`, person);
  }

  deletePerson(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/people/${id}`);
  }

  getRegions(): Observable<Region[]> {
    return this.http.get<Region[]>(`${this.apiUrl}/regions`);
  }

  getCommunes(regionId: string): Observable<Commune[]> {
    return this.http.get<Commune[]>(`${this.apiUrl}/regions/${regionId}/communes`);
  }
}

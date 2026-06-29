import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Paginate } from '../models/paginate.model';
import { Person } from '../models/person.model';
import { Region } from '../models/region.model';
import { Commune } from '../models/commune.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  constructor(private http: HttpClient) {}

  getPaginatePeople(page: number = 0, size: number = 10, search: string = ''): Observable<Paginate> {
    let params = new HttpParams().set('page', page.toString()).set('size', size.toString());
    if (search) params = params.set('search', search);
    return this.http.get<Paginate>(`${environment.apiUrl}/people/paginate`, { params });
  }

  getPeople(): Observable<Person[]> {
    return this.http.get<Person[]>(`${environment.apiUrl}/people`);
  }

  getPerson(id: string): Observable<Person> {
    return this.http.get<Person>(`${environment.apiUrl}/people/${id}`);
  }

  createPerson(person: Person): Observable<Person> {
    return this.http.post<Person>(`${environment.apiUrl}/people`, person);
  }

  deletePerson(id: string): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/people/${id}`);
  }

  getRegions(): Observable<Region[]> {
    return this.http.get<Region[]>(`${environment.apiUrl}/regions`);
  }

  getCommunes(regionId: string): Observable<Commune[]> {
    return this.http.get<Commune[]>(`${environment.apiUrl}/regions/${regionId}/communes`);
  }
}
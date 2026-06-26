import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Region } from '../models/region.model';

@Injectable({
  providedIn: 'root'
})
export class RegionService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getRegions(): Observable<Region[]> {
    return this.http.get<Region[]>(`${this.apiUrl}/regions`);
  }
}

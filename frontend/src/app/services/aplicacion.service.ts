import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Aplicacion } from '../models/models';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AplicacionService {
  private apiUrl = `${environment.apiUrl}/aplicaciones`;

  constructor(private http: HttpClient) { }

  getAll(): Observable<Aplicacion[]> {
    return this.http.get<Aplicacion[]>(this.apiUrl);
  }

  getById(id: number): Observable<Aplicacion> {
    return this.http.get<Aplicacion>(`${this.apiUrl}/${id}`);
  }

  create(aplicacion: Aplicacion): Observable<Aplicacion> {
    return this.http.post<Aplicacion>(this.apiUrl, aplicacion);
  }

  update(id: number, aplicacion: Aplicacion): Observable<Aplicacion> {
    return this.http.put<Aplicacion>(`${this.apiUrl}/${id}`, aplicacion);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}

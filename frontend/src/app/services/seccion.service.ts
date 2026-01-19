import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Seccion } from '../models/models';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SeccionService {
  private apiUrl = `${environment.apiUrl}/secciones`;

  constructor(private http: HttpClient) { }

  getAll(): Observable<Seccion[]> {
    return this.http.get<Seccion[]>(this.apiUrl);
  }

  getById(id: number): Observable<Seccion> {
    return this.http.get<Seccion>(`${this.apiUrl}/${id}`);
  }

  getByAplicacion(aplicacionId: number): Observable<Seccion[]> {
    return this.http.get<Seccion[]>(`${this.apiUrl}/aplicacion/${aplicacionId}`);
  }

  create(seccion: Seccion): Observable<Seccion> {
    return this.http.post<Seccion>(this.apiUrl, seccion);
  }

  update(id: number, seccion: Seccion): Observable<Seccion> {
    return this.http.put<Seccion>(`${this.apiUrl}/${id}`, seccion);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}

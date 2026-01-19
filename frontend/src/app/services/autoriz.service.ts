import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Autoriz } from '../models/models';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AutorizService {
  private apiUrl = `${environment.apiUrl}/autorizaciones`;

  constructor(private http: HttpClient) { }

  getAll(): Observable<Autoriz[]> {
    return this.http.get<Autoriz[]>(this.apiUrl);
  }

  getById(id: number): Observable<Autoriz> {
    return this.http.get<Autoriz>(`${this.apiUrl}/${id}`);
  }

  getByPerfil(perfilId: number): Observable<Autoriz[]> {
    return this.http.get<Autoriz[]>(`${this.apiUrl}/perfil/${perfilId}`);
  }

  getBySeccion(seccionId: number): Observable<Autoriz[]> {
    return this.http.get<Autoriz[]>(`${this.apiUrl}/seccion/${seccionId}`);
  }

  create(autoriz: Autoriz): Observable<Autoriz> {
    return this.http.post<Autoriz>(this.apiUrl, autoriz);
  }

  update(id: number, autoriz: Autoriz): Observable<Autoriz> {
    return this.http.put<Autoriz>(`${this.apiUrl}/${id}`, autoriz);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}

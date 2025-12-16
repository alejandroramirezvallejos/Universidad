import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DtoNotificacion } from '../../models/backend-dtos';

@Injectable({
  providedIn: 'root'
})
export class BackendNotificacionService {
  private apiUrl = 'http://localhost:8080/api/notificaciones';

  constructor(private http: HttpClient) { }

  notificar(dto: DtoNotificacion): Observable<void> {
    return this.http.post<void>(this.apiUrl, dto);
  }
}

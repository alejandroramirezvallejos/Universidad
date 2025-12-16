/**
 * Servicio de Usuarios
 * Conecta con ControladorUsuario del backend
 * Endpoints: /api/usuarios
 */

import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap, catchError, of } from 'rxjs';

export interface Usuario {
  codigo: string;
  nombre: string;
  apellido: string;
  email: string;
  contrasenna?: string;
  rol: 'ESTUDIANTE' | 'DOCENTE' | 'DIRECTOR';
}

export interface ActualizarUsuarioRequest {
  nombre?: string;
  apellido?: string;
  email?: string;
  contrasenna?: string;
  especialidad?: string;
  departamento?: string;
}

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {
  private http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/usuarios';

  private _usuarioActual = signal<Usuario | null>(null);
  readonly usuarioActual = this._usuarioActual.asReadonly();

  /**
   * Obtener usuario por código
   */
  getUsuario(codigo: string): Observable<Usuario | null> {
    return this.http.get<Usuario>(`${this.apiUrl}/${codigo}`).pipe(
      tap(usuario => this._usuarioActual.set(usuario)),
      catchError(error => {
        console.error('Error al obtener usuario:', error);
        return of(null);
      })
    );
  }

  /**
   * Actualizar datos del usuario
   */
  actualizar(codigo: string, datos: ActualizarUsuarioRequest): Observable<Usuario | null> {
    return this.http.put<Usuario>(`${this.apiUrl}/${codigo}`, datos).pipe(
      tap(usuario => {
        if (usuario) {
          this._usuarioActual.set(usuario);
        }
      }),
      catchError(error => {
        console.error('Error al actualizar usuario:', error);
        return of(null);
      })
    );
  }

  /**
   * Cambiar contraseña
   */
  cambiarContrasenna(codigo: string, nuevaContrasenna: string): Observable<Usuario | null> {
    return this.actualizar(codigo, { contrasenna: nuevaContrasenna });
  }

  /**
   * Actualizar email
   */
  actualizarEmail(codigo: string, nuevoEmail: string): Observable<Usuario | null> {
    return this.actualizar(codigo, { email: nuevoEmail });
  }

  /**
   * Limpiar usuario actual
   */
  limpiar(): void {
    this._usuarioActual.set(null);
  }
}


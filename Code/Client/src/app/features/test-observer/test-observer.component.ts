import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BackendNotificacionService } from '../../core/services/backend-notificacion.service';
import { DtoNotificacion, DtoEstudiante, DtoMateria } from '../../models/backend-dtos';

@Component({
  selector: 'app-test-observer',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './test-observer.component.html',
  styleUrls: ['./test-observer.component.scss']
})
export class TestObserverComponent {
  estudiante: DtoEstudiante = {
    codigo: 'EST-001',
    nombre: 'Juan Perez',
    email: 'juan@test.com'
  };

  materia: DtoMateria = {
    codigo: 'MAT-101',
    nombre: 'Ingeniería de Software',
    creditos: 5,
    semestre: 4
  };

  notaFinal: number = 0;
  mensaje: string = '';

  constructor(private notificacionService: BackendNotificacionService) {}

  enviarNotificacion() {
    const dto: DtoNotificacion = {
      estudiante: this.estudiante,
      materia: this.materia,
      notaFinal: this.notaFinal
    };

    this.notificacionService.notificar(dto).subscribe({
      next: () => {
        this.mensaje = 'Notificación enviada correctamente. Revisa la consola del servidor.';
      },
      error: (err) => {
        this.mensaje = 'Error al enviar notificación: ' + err.message;
      }
    });
  }
}


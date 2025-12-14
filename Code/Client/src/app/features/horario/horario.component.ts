/**
 * Componente de Horario del Estudiante
 * Muestra el horario semanal de las materias inscritas
 * 
 * Heurística Nielsen #6: Reconocimiento antes que recuerdo
 * - Visualización clara del horario completo
 * 
 * Heurística Nielsen #8: Diseño estético y minimalista
 * - Grid visual tipo calendario universitario
 */

import { Component, OnInit, computed, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatriculaService } from '../../core/services/matricula.service';
import { NotificacionService } from '../../core/services/notificacion.service';
import { AuthService } from '../../core/services/auth.service';
import { Horario, DiaSemana } from '../../models';

interface BloqueHorario {
  materiaId: number;
  materiaCodigo: string;
  materiaNombre: string;
  grupoId: number;
  grupoCodigo: string;
  docente: string;
  aula: string;
  horaInicio: string;
  horaFin: string;
  dia: DiaSemana;
  color: string;
}

@Component({
  selector: 'app-horario',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <div class="horario-page">
      <!-- Encabezado -->
      <header class="pagina-header">
        <div class="header-info">
          <h1>Mi Horario</h1>
          <p>{{ authService.nombreCompleto() }} • Gestión II-2025</p>
        </div>
        <div class="header-acciones">
          <button class="btn btn-outline" (click)="descargarHorario()">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"></path>
              <polyline points="7 10 12 15 17 10"></polyline>
              <line x1="12" y1="15" x2="12" y2="3"></line>
            </svg>
            Descargar Horario
          </button>
          <button class="btn btn-primario" (click)="imprimirHorario()">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <polyline points="6 9 6 2 18 2 18 9"></polyline>
              <path d="M6 18H4a2 2 0 0 1-2-2v-5a2 2 0 0 1 2-2h16a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2h-2"></path>
              <rect x="6" y="14" width="12" height="8"></rect>
            </svg>
            Imprimir
          </button>
        </div>
      </header>

      <!-- Resumen de materias inscritas -->
      <section class="resumen-materias card">
        <h3 class="card-titulo">Materias Inscritas ({{ materiasInscritas().length }})</h3>
        <div class="materias-lista">
          <div *ngFor="let materia of materiasInscritas()" class="materia-item">
            <div class="materia-color" [style.background-color]="materia.color"></div>
            <div class="materia-info">
              <span class="materia-codigo">{{ materia.codigo }}</span>
              <span class="materia-nombre">{{ materia.nombre }}</span>
            </div>
            <div class="materia-detalle">
              <span class="materia-grupo">Grupo {{ materia.grupo }}</span>
              <span class="materia-creditos">{{ materia.creditos }} créditos</span>
            </div>
          </div>
        </div>
      </section>

      <!-- Grid de horario semanal -->
      <section class="horario-grid card">
        <h3 class="card-titulo">Horario Semanal</h3>
        
        <!-- Mensaje si no hay materias -->
        <div *ngIf="materiasInscritas().length === 0" class="sin-horario">
          <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
            <line x1="16" y1="2" x2="16" y2="6"></line>
            <line x1="8" y1="2" x2="8" y2="6"></line>
            <line x1="3" y1="10" x2="21" y2="10"></line>
          </svg>
          <p>No tienes materias inscritas aún</p>
          <a routerLink="/matricula" class="btn btn-primario">Ir a Matrícula</a>
        </div>

        <!-- Grid de horario -->
        <div *ngIf="materiasInscritas().length > 0" class="horario-contenedor">
          <div class="horario-tabla">
            <!-- Encabezado de días -->
            <div class="horario-header">
              <div class="hora-columna">Hora</div>
              <div class="dia-columna" *ngFor="let dia of dias">{{ dia }}</div>
            </div>

            <!-- Filas de horario -->
            <div class="horario-body">
              <div *ngFor="let hora of horasDisponibles" class="horario-fila">
                <div class="hora-celda">
                  {{ hora }}
                </div>
                <div *ngFor="let dia of diasCompletos" class="dia-celda">
                  <div *ngFor="let bloque of obtenerBloquesEnHora(dia, hora)" 
                       class="bloque-clase"
                       [style.background-color]="bloque.color"
                       [class.bloque-multiple]="obtenerBloquesEnHora(dia, hora).length > 1">
                    <!-- Mostrar información completa en TODAS las horas que ocupa la clase -->
                    <div class="bloque-contenido">
                      <span class="bloque-codigo">{{ bloque.materiaCodigo }}</span>
                      <span class="bloque-nombre">{{ bloque.materiaNombre }}</span>
                      <span class="bloque-aula">{{ bloque.aula }}</span>
                      <span class="bloque-tiempo">{{ bloque.horaInicio }} - {{ bloque.horaFin }}</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      <!-- Lista detallada por día -->
      <section class="horario-detalle card">
        <h3 class="card-titulo">Detalle por Día</h3>
        <div class="dias-detalle">
          <div *ngFor="let dia of diasCompletos" class="dia-detalle">
            <div class="dia-header">
              <h4>{{ formatearDiaCompleto(dia) }}</h4>
              <span class="dia-cantidad">{{ obtenerClasesPorDia(dia).length }} clases</span>
            </div>
            <div *ngIf="obtenerClasesPorDia(dia).length === 0" class="dia-vacio">
              Sin clases este día
            </div>
            <div *ngIf="obtenerClasesPorDia(dia).length > 0" class="dia-clases">
              <div *ngFor="let bloque of obtenerClasesPorDia(dia)" class="clase-detalle">
                <div class="clase-tiempo">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <circle cx="12" cy="12" r="10"></circle>
                    <polyline points="12 6 12 12 16 14"></polyline>
                  </svg>
                  {{ bloque.horaInicio }} - {{ bloque.horaFin }}
                </div>
                <div class="clase-info">
                  <div class="clase-color" [style.background-color]="bloque.color"></div>
                  <div class="clase-datos">
                    <span class="clase-codigo">{{ bloque.materiaCodigo }}</span>
                    <span class="clase-nombre">{{ bloque.materiaNombre }}</span>
                    <span class="clase-docente">
                      <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                        <circle cx="12" cy="7" r="4"></circle>
                      </svg>
                      {{ bloque.docente }}
                    </span>
                    <span class="clase-aula">
                      <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <path d="m3 9 9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
                      </svg>
                      {{ bloque.aula }}
                    </span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  `,
  styles: [`
    .horario-page {
      max-width: var(--ancho-maximo);
      margin: 0 auto;
    }

    .pagina-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: var(--espaciado-xl);
      flex-wrap: wrap;
      gap: var(--espaciado-md);
    }

    .header-info h1 {
      font-size: var(--texto-2xl);
      margin-bottom: var(--espaciado-xs);
    }

    .header-info p {
      color: var(--color-texto-secundario);
      margin: 0;
    }

    .header-acciones {
      display: flex;
      gap: var(--espaciado-sm);
    }

    /* Resumen de materias */
    .resumen-materias {
      margin-bottom: var(--espaciado-xl);
    }

    .card-titulo {
      font-size: var(--texto-lg);
      font-weight: 600;
      margin-bottom: var(--espaciado-md);
    }

    .materias-lista {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
      gap: var(--espaciado-md);
    }

    .materia-item {
      display: flex;
      align-items: center;
      gap: var(--espaciado-md);
      padding: var(--espaciado-md);
      background-color: var(--color-fondo);
      border-radius: var(--radio-md);
      border: 1px solid var(--color-borde);
    }

    .materia-color {
      width: 4px;
      height: 48px;
      border-radius: var(--radio-sm);
    }

    .materia-info {
      display: flex;
      flex-direction: column;
      flex: 1;
    }

    .materia-codigo {
      font-weight: 600;
      color: var(--color-primario);
      font-size: var(--texto-sm);
    }

    .materia-nombre {
      font-size: var(--texto-sm);
      color: var(--color-texto);
    }

    .materia-detalle {
      display: flex;
      flex-direction: column;
      align-items: flex-end;
      font-size: var(--texto-xs);
      color: var(--color-texto-secundario);
    }

    /* Grid de horario */
    .horario-grid {
      margin-bottom: var(--espaciado-xl);
    }

    .horario-contenedor {
      overflow-x: auto;
    }

    .horario-tabla {
      min-width: 800px;
    }

    .horario-header {
      display: grid;
      grid-template-columns: 80px repeat(6, 1fr);
      gap: 2px;
      margin-bottom: 2px;
    }

    .hora-columna,
    .dia-columna {
      padding: var(--espaciado-md);
      background-color: var(--color-primario-light);
      color: var(--color-primario);
      font-weight: 600;
      text-align: center;
      font-size: var(--texto-sm);
      border-radius: var(--radio-sm);
    }

    .horario-body {
      display: flex;
      flex-direction: column;
      gap: 2px;
    }

    .horario-fila {
      display: grid;
      grid-template-columns: 80px repeat(6, 1fr);
      gap: 2px;
    }

    .hora-celda {
      padding: var(--espaciado-sm);
      background-color: var(--color-fondo);
      border: 1px solid var(--color-borde);
      border-radius: var(--radio-sm);
      font-size: var(--texto-xs);
      font-weight: 500;
      text-align: center;
      display: flex;
      align-items: center;
      justify-content: center;
      color: var(--color-texto-secundario);
    }

    .dia-celda {
      padding: var(--espaciado-xs);
      background-color: var(--color-fondo-card);
      border: 1px solid var(--color-borde);
      border-radius: var(--radio-sm);
      min-height: 80px;
      display: flex;
      flex-direction: column;
      gap: var(--espaciado-xs);
      position: relative;
    }

    .bloque-clase {
      padding: var(--espaciado-sm);
      border-radius: var(--radio-sm);
      color: white;
      font-size: var(--texto-xs);
      cursor: pointer;
      transition: transform var(--transicion-rapida);
      box-shadow: var(--sombra-md);
      min-height: 70px;
      display: flex;
      align-items: center;
      justify-content: center;
      font-weight: 500;
    }

    .bloque-clase:hover {
      transform: scale(1.02);
      box-shadow: var(--sombra-md);
    }

    .bloque-contenido {
      display: flex;
      flex-direction: column;
      gap: 2px;
    }

    .bloque-codigo {
      font-weight: 700;
      font-size: 10px;
      opacity: 0.9;
    }

    .bloque-nombre {
      font-weight: 500;
      line-height: 1.2;
    }

    .bloque-aula,
    .bloque-tiempo {
      font-size: 10px;
      opacity: 0.8;
    }

    /* Detalle por día */
    .dias-detalle {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
      gap: var(--espaciado-lg);
    }

    .dia-detalle {
      border: 1px solid var(--color-borde);
      border-radius: var(--radio-md);
      overflow: hidden;
    }

    .dia-header {
      padding: var(--espaciado-md);
      background-color: var(--color-fondo);
      border-bottom: 1px solid var(--color-borde);
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .dia-header h4 {
      margin: 0;
      font-size: var(--texto-base);
      font-weight: 600;
    }

    .dia-cantidad {
      font-size: var(--texto-sm);
      color: var(--color-texto-secundario);
      background-color: var(--color-primario-light);
      color: var(--color-primario);
      padding: 2px var(--espaciado-sm);
      border-radius: var(--radio-full);
      font-weight: 600;
    }

    .dia-vacio {
      padding: var(--espaciado-lg);
      text-align: center;
      color: var(--color-texto-secundario);
      font-size: var(--texto-sm);
    }

    .dia-clases {
      padding: var(--espaciado-md);
      display: flex;
      flex-direction: column;
      gap: var(--espaciado-md);
    }

    .clase-detalle {
      display: flex;
      gap: var(--espaciado-md);
    }

    .clase-tiempo {
      display: flex;
      align-items: center;
      gap: var(--espaciado-xs);
      font-weight: 600;
      color: var(--color-primario);
      font-size: var(--texto-sm);
      min-width: 120px;
    }

    .clase-info {
      display: flex;
      gap: var(--espaciado-sm);
      flex: 1;
    }

    .clase-color {
      width: 4px;
      border-radius: var(--radio-sm);
    }

    .clase-datos {
      display: flex;
      flex-direction: column;
      gap: 4px;
      flex: 1;
    }

    .clase-codigo {
      font-weight: 600;
      color: var(--color-primario);
      font-size: var(--texto-sm);
    }

    .clase-nombre {
      font-weight: 500;
      font-size: var(--texto-sm);
    }

    .clase-docente,
    .clase-aula {
      display: flex;
      align-items: center;
      gap: var(--espaciado-xs);
      font-size: var(--texto-xs);
      color: var(--color-texto-secundario);
    }

    /* Sin horario */
    .sin-horario {
      padding: var(--espaciado-2xl);
      display: flex;
      flex-direction: column;
      align-items: center;
      gap: var(--espaciado-md);
      text-align: center;
    }

    .sin-horario svg {
      color: var(--color-texto-claro);
    }

    .sin-horario p {
      color: var(--color-texto-secundario);
      margin: 0;
    }

    /* Responsive */
    @media (max-width: 768px) {
      .pagina-header {
        flex-direction: column;
        align-items: flex-start;
      }

      .header-acciones {
        width: 100%;
      }

      .header-acciones .btn {
        flex: 1;
      }

      .materias-lista {
        grid-template-columns: 1fr;
      }

      .dias-detalle {
        grid-template-columns: 1fr;
      }
    }

    @media print {
      .header-acciones {
        display: none;
      }

      .horario-detalle {
        page-break-before: always;
      }
    }
  `]
})
export class HorarioComponent implements OnInit {
  dias = ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'];
  diasCompletos: DiaSemana[] = ['LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES', 'SABADO'];
  horasDisponibles = ['07:00', '08:00', '09:00', '10:00', '11:00', '12:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00'];
  
  private colores = [
    '#2563eb', // Azul
    '#16a34a', // Verde
    '#ea580c', // Naranja
    '#dc2626', // Rojo
    '#0891b2', // Cyan
    '#7c3aed', // Violeta
    '#db2777', // Rosa
    '#059669'  // Esmeralda
  ];

  constructor(
    public authService: AuthService,
    private matriculaService: MatriculaService,
    private notificacion: NotificacionService
  ) {}

  ngOnInit(): void {
    // Cargar matrículas del estudiante
    this.cargarMatriculas();
  }

  private _matriculas = signal<any[]>([]);

  async cargarMatriculas() {
    try {
      const matriculas = await this.matriculaService.obtenerMatriculasActivas();
      this._matriculas.set(matriculas);
    } catch (error) {
      console.error('Error al cargar matrículas:', error);
      this.notificacion.error('Error al cargar las matrículas');
    }
  }

  materiasInscritas = computed(() => {
    const matriculas = this._matriculas();
    return matriculas.map((m: any, index: number) => ({
      codigo: m.grupo.materia.codigo,
      nombre: m.grupo.materia.nombre,
      grupo: m.grupo.codigo,
      creditos: m.grupo.materia.creditos,
      color: this.colores[index % this.colores.length]
    }));
  });

  bloquesHorario = computed(() => {
    const matriculas = this._matriculas();
    const bloques: BloqueHorario[] = [];

    matriculas.forEach((m: any, index: number) => {
      const color = this.colores[index % this.colores.length];
      m.grupo.horarios.forEach((horario: any) => {
        bloques.push({
          materiaId: m.grupo.materia.id,
          materiaCodigo: m.grupo.materia.codigo,
          materiaNombre: m.grupo.materia.nombre,
          grupoId: m.grupo.id,
          grupoCodigo: m.grupo.codigo,
          docente: m.grupo.docente.nombre + ' ' + m.grupo.docente.apellido,
          aula: m.grupo.aula.nombre,
          horaInicio: horario.horaInicio,
          horaFin: horario.horaFin,
          dia: horario.dia,
          color: color
        });
      });
    });

    return bloques;
  });

  obtenerBloquesEnHora(dia: DiaSemana, hora: string): BloqueHorario[] {
    return this.bloquesHorario().filter(bloque => {
      if (bloque.dia !== dia) return false;
      
      // Convertir horas a números para comparación
      const horaActualNum = parseInt(hora.split(':')[0]);
      const horaInicioNum = parseInt(bloque.horaInicio.split(':')[0]);
      const horaFinNum = parseInt(bloque.horaFin.split(':')[0]);
      
      // La clase ocupa TODAS las horas desde inicio hasta fin (inclusive)
      // Por ejemplo: clase 08:00-10:00 debe aparecer en las horas 8, 9 Y 10
      return horaActualNum >= horaInicioNum && horaActualNum <= horaFinNum;
    });
  }

  obtenerClasesPorDia(dia: DiaSemana): BloqueHorario[] {
    return this.bloquesHorario()
      .filter(b => b.dia === dia)
      .sort((a, b) => a.horaInicio.localeCompare(b.horaInicio));
  }

  formatearDiaCompleto(dia: DiaSemana): string {
    const dias: Record<DiaSemana, string> = {
      'LUNES': 'Lunes',
      'MARTES': 'Martes',
      'MIERCOLES': 'Miércoles',
      'JUEVES': 'Jueves',
      'VIERNES': 'Viernes',
      'SABADO': 'Sábado'
    };
    return dias[dia];
  }

  descargarHorario(): void {
    const estudiante = this.authService.nombreCompleto();
    let contenido = `
HORARIO ACADÉMICO
=================

Estudiante: ${estudiante}
Gestión: II-2025
Fecha de emisión: ${new Date().toLocaleDateString('es-BO')}

MATERIAS INSCRITAS
------------------
`;

    this.materiasInscritas().forEach((m, i) => {
      contenido += `${i + 1}. ${m.codigo} - ${m.nombre}
   Grupo: ${m.grupo} | Créditos: ${m.creditos}
`;
    });

    contenido += `

HORARIO SEMANAL
---------------
`;

    this.diasCompletos.forEach(dia => {
      const clases = this.obtenerClasesPorDia(dia);
      if (clases.length > 0) {
        contenido += `
${this.formatearDiaCompleto(dia).toUpperCase()}
${'='.repeat(this.formatearDiaCompleto(dia).length)}
`;
        clases.forEach(clase => {
          contenido += `  ${clase.horaInicio} - ${clase.horaFin}
    ${clase.materiaCodigo} - ${clase.materiaNombre}
    Docente: ${clase.docente}
    Aula: ${clase.aula}

`;
        });
      }
    });

    contenido += `
=================
Documento generado automáticamente
Sistema Universitario
`;

    // Crear y descargar archivo
    const blob = new Blob([contenido], { type: 'text/plain; charset=utf-8' });
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = `Horario_${estudiante.replace(/\s+/g, '_')}.txt`;
    link.click();
    window.URL.revokeObjectURL(url);

    this.notificacion.exito('Horario descargado exitosamente');
  }

  imprimirHorario(): void {
    window.print();
    this.notificacion.info('Preparando impresión del horario...');
  }

  /**
   * Determina si la hora actual es la hora de inicio del bloque
   * Esto ayuda a diferenciar visualmente entre la primera celda de un bloque
   * y las celdas de continuación
   */
  esHoraInicio(bloque: BloqueHorario, hora: string): boolean {
    const horaActual = parseInt(hora.split(':')[0]);
    const horaInicio = parseInt(bloque.horaInicio.split(':')[0]);
    return horaActual === horaInicio;
  }
}

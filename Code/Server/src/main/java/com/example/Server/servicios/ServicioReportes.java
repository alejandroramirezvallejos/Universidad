package com.example.Server.servicios;

import com.example.Server.dtos.*;
import com.example.Server.modelos.*;
import com.example.Server.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para generar reportes del sistema
 * Proporciona estadísticas y análisis de datos académicos
 */
@Service
public class ServicioReportes {

    @Autowired
    private RepositorioEstudiante repositorioEstudiante;

    @Autowired
    private RepositorioCarrera repositorioCarrera;

    @Autowired
    private RepositorioMatricula repositorioMatricula;

    @Autowired
    private RepositorioGestion repositorioGestion;

    @Autowired
    private RepositorioParaleloMateria repositorioParalelo;

    /**
     * Genera reporte de estudiantes por carrera
     */
    public DtoReporteEstudiantesPorCarrera reporteEstudiantesPorCarrera(String codigoCarrera) {
        Carrera carrera = repositorioCarrera.buscarPorCodigo(codigoCarrera);
        
        if (carrera == null) {
            return null;
        }

        DtoReporteEstudiantesPorCarrera reporte = new DtoReporteEstudiantesPorCarrera();
        reporte.setCodigoCarrera(carrera.getCodigo());
        reporte.setNombreCarrera(carrera.getNombre());

        // Obtener estudiantes de la carrera
        List<Estudiante> estudiantesCarrera = repositorioEstudiante.getEstudiantes().stream()
                .filter(e -> e.getCarrera() != null && 
                             e.getCarrera().getCodigo().equals(codigoCarrera))
                .collect(Collectors.toList());

        reporte.setTotalEstudiantes(estudiantesCarrera.size());

        // Contar por semestre
        reporte.setEstudiantesPorSemestre1(contarPorSemestre(estudiantesCarrera, 1));
        reporte.setEstudiantesPorSemestre2(contarPorSemestre(estudiantesCarrera, 2));
        reporte.setEstudiantesPorSemestre3(contarPorSemestre(estudiantesCarrera, 3));
        reporte.setEstudiantesPorSemestre4(contarPorSemestre(estudiantesCarrera, 4));
        reporte.setEstudiantesPorSemestre5(contarPorSemestre(estudiantesCarrera, 5));
        reporte.setEstudiantesPorSemestre6(contarPorSemestre(estudiantesCarrera, 6));
        reporte.setEstudiantesPorSemestre7(contarPorSemestre(estudiantesCarrera, 7));
        reporte.setEstudiantesPorSemestre8(contarPorSemestre(estudiantesCarrera, 8));

        // Lista detallada (simplificada por ahora)
        reporte.setEstudiantes(new ArrayList<>());

        return reporte;
    }

    /**
     * Genera reporte de inscripciones por gestión
     */
    public DtoReporteInscripciones reporteInscripcionesPorGestion(String codigoGestion) {
        Gestion gestion = repositorioGestion.buscarPorCodigo(codigoGestion).orElse(null);
        
        if (gestion == null) {
            return null;
        }

        DtoReporteInscripciones reporte = new DtoReporteInscripciones();
        reporte.setCodigoGestion(gestion.getCodigo());
        reporte.setNombreGestion(gestion.getNombre());

        // Obtener todas las matrículas
        List<Matricula> todasMatriculas = repositorioMatricula.getMatriculas();

        // Filtrar por gestión (simplificado - asumimos que paralelo tiene gestión)
        List<Matricula> matriculasGestion = todasMatriculas.stream()
                .filter(m -> m.getParaleloMateria() != null && 
                             m.getParaleloMateria().getGestion() != null &&
                             m.getParaleloMateria().getGestion().getCodigo().equals(codigoGestion))
                .collect(Collectors.toList());

        reporte.setTotalInscripciones(matriculasGestion.size());

        // Contar por estado
        int pendientes = (int) matriculasGestion.stream()
                .filter(m -> "PENDIENTE".equals(m.getEstado()))
                .count();
        int aceptadas = (int) matriculasGestion.stream()
                .filter(m -> "ACEPTADA".equals(m.getEstado()))
                .count();
        int rechazadas = (int) matriculasGestion.stream()
                .filter(m -> "RECHAZADA".equals(m.getEstado()))
                .count();

        reporte.setInscripcionesPendientes(pendientes);
        reporte.setInscripcionesAceptadas(aceptadas);
        reporte.setInscripcionesRechazadas(rechazadas);

        // Lista detallada (simplificada por ahora)
        reporte.setInscripciones(new ArrayList<>());

        return reporte;
    }

    /**
     * Genera reporte de rendimiento académico por paralelo
     */
    public DtoReporteRendimiento reporteRendimientoPorParalelo(String codigoParalelo) {
        ParaleloMateria paralelo = repositorioParalelo.buscarPorCodigo(codigoParalelo);
        
        if (paralelo == null) {
            return null;
        }

        DtoReporteRendimiento reporte = new DtoReporteRendimiento();
        reporte.setCodigoParalelo(paralelo.getCodigo());
        
        if (paralelo.getMateria() != null) {
            reporte.setCodigoMateria(paralelo.getMateria().getCodigo());
            reporte.setNombreMateria(paralelo.getMateria().getNombre());
        }
        
        if (paralelo.getDocente() != null) {
            reporte.setNombreDocente(paralelo.getDocente().getNombre() + " " + 
                                    paralelo.getDocente().getApellido());
        }

        // Estadísticas de estudiantes
        List<Estudiante> estudiantes = paralelo.getEstudiantes();
        int total = estudiantes != null ? estudiantes.size() : 0;
        
        reporte.setTotalEstudiantes(total);
        reporte.setPromedioGeneral(0.0); // TODO: Calcular desde evaluaciones
        reporte.setAprobados(0); // TODO: Calcular desde notas finales
        reporte.setReprobados(0);
        reporte.setSinCalificar(total);

        // Lista detallada (simplificada por ahora)
        reporte.setEstudiantes(new ArrayList<>());

        return reporte;
    }

    /**
     * Lista todos los reportes disponibles (índice)
     */
    public List<String> listarReportesDisponibles() {
        List<String> reportes = new ArrayList<>();
        reportes.add("estudiantes-por-carrera");
        reportes.add("inscripciones-por-gestion");
        reportes.add("rendimiento-por-paralelo");
        reportes.add("estadisticas-generales");
        return reportes;
    }

    /**
     * Cuenta estudiantes por semestre
     */
    private int contarPorSemestre(List<Estudiante> estudiantes, int semestre) {
        return (int) estudiantes.stream()
                .filter(e -> e.getSemestre() == semestre)
                .count();
    }
}

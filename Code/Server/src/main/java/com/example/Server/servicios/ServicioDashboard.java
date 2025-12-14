package com.example.Server.servicios;

import com.example.Server.dtos.DtoDashboardEstudiante;
import com.example.Server.dtos.DtoDashboardDocente;
import com.example.Server.modelos.*;
import com.example.Server.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

/**
 * Servicio para generar estadísticas del dashboard
 * Proporciona métricas según el rol del usuario
 */
@Service
public class ServicioDashboard {

    @Autowired
    private RepositorioEstudiante repositorioEstudiante;

    @Autowired
    private RepositorioDocente repositorioDocente;

    @Autowired
    private RepositorioParaleloMateria repositorioParalelo;

    /**
     * Genera dashboard para estudiante
     */
    public DtoDashboardEstudiante generarDashboardEstudiante(String codigoEstudiante) {
        Estudiante estudiante = repositorioEstudiante.buscarPorCodigo(codigoEstudiante);
        
        if (estudiante == null) {
            return null;
        }

        DtoDashboardEstudiante dashboard = new DtoDashboardEstudiante();
        dashboard.setCodigoEstudiante(estudiante.getCodigo());
        dashboard.setNombreCompleto(estudiante.getNombre() + " " + estudiante.getApellido());
        dashboard.setSemestre(estudiante.getSemestre());
        
        if (estudiante.getCarrera() != null) {
            dashboard.setCarrera(estudiante.getCarrera().getNombre());
        }

        // Estadísticas de materias
        int materiasInscritas = estudiante.getMateriasInscritas() != null ? 
                                estudiante.getMateriasInscritas().size() : 0;
        int materiasAprobadas = estudiante.getMateriasAprobadas() != null ? 
                                estudiante.getMateriasAprobadas().size() : 0;

        dashboard.setMateriasInscritas(materiasInscritas);
        dashboard.setMateriasAprobadas(materiasAprobadas);

        // Calcular créditos
        int creditosInscritos = calcularCreditosInscritos(estudiante);
        int creditosAprobados = calcularCreditosAprobados(estudiante);

        dashboard.setCreditosInscritos(creditosInscritos);
        dashboard.setCreditosAprobados(creditosAprobados);

        // Promedio general (simplificado - sería mejor con historial de notas)
        dashboard.setPromedioGeneral(0.0); // TODO: Calcular desde historial de notas

        // Listas detalladas vacías por ahora
        dashboard.setMateriasActuales(new ArrayList<>());
        dashboard.setCalificacionesRecientes(new ArrayList<>());

        return dashboard;
    }

    /**
     * Genera dashboard para docente
     */
    public DtoDashboardDocente generarDashboardDocente(String codigoDocente) {
        Docente docente = repositorioDocente.buscarPorCodigo(codigoDocente);
        
        if (docente == null) {
            return null;
        }

        DtoDashboardDocente dashboard = new DtoDashboardDocente();
        dashboard.setCodigoDocente(docente.getCodigo());
        dashboard.setNombreCompleto(docente.getNombre() + " " + docente.getApellido());
        dashboard.setDepartamento(docente.getDepartamento());
        dashboard.setEspecialidad(docente.getEspecialidad());

        // Buscar paralelos del docente
        long paralelosActivos = repositorioParalelo.getParalelos().stream()
                .filter(p -> p.getDocente() != null && 
                             p.getDocente().getCodigo().equals(codigoDocente))
                .count();

        dashboard.setParalelosActivos((int) paralelosActivos);

        // Contar total de estudiantes
        int totalEstudiantes = repositorioParalelo.getParalelos().stream()
                .filter(p -> p.getDocente() != null && 
                             p.getDocente().getCodigo().equals(codigoDocente))
                .mapToInt(p -> p.getEstudiantes() != null ? p.getEstudiantes().size() : 0)
                .sum();

        dashboard.setTotalEstudiantes(totalEstudiantes);

        // Contar materias únicas
        long materiasImpartidas = repositorioParalelo.getParalelos().stream()
                .filter(p -> p.getDocente() != null && 
                             p.getDocente().getCodigo().equals(codigoDocente))
                .map(p -> p.getMateria().getCodigo())
                .distinct()
                .count();

        dashboard.setMateriasImpartidas((int) materiasImpartidas);

        // Listas detalladas vacías por ahora
        dashboard.setParalelosActuales(new ArrayList<>());
        dashboard.setEstadisticasMaterias(new ArrayList<>());

        return dashboard;
    }

    /**
     * Calcula total de créditos inscritos
     */
    private int calcularCreditosInscritos(Estudiante estudiante) {
        if (estudiante.getMateriasInscritas() == null) {
            return 0;
        }

        return estudiante.getMateriasInscritas().stream()
                .mapToInt(Materia::getCreditos)
                .sum();
    }

    /**
     * Calcula total de créditos aprobados
     */
    private int calcularCreditosAprobados(Estudiante estudiante) {
        if (estudiante.getMateriasAprobadas() == null) {
            return 0;
        }

        return estudiante.getMateriasAprobadas().stream()
                .mapToInt(Materia::getCreditos)
                .sum();
    }
}

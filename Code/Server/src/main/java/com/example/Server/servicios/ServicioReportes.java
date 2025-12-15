package com.example.Server.servicios;
import com.example.Server.dtos.*;
import com.example.Server.modelos.*;
import com.example.Server.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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

    public DtoReporteEstudiantesPorCarrera reporteEstudiantesPorCarrera(String codigoCarrera) {
        Carrera carrera = repositorioCarrera.buscarPorCodigo(codigoCarrera);
        
        if (carrera == null)
            return null;

        DtoReporteEstudiantesPorCarrera reporte = new DtoReporteEstudiantesPorCarrera();
        reporte.setCodigoCarrera(carrera.getCodigo());
        reporte.setNombreCarrera(carrera.getNombre());
        List<Estudiante> estudiantesCarrera = new ArrayList<>();

        for (Estudiante estudiante : repositorioEstudiante.getEstudiantes())
            if (estudiante.getCarrera() != null && estudiante.getCarrera().getCodigo().equals(codigoCarrera))
                estudiantesCarrera.add(estudiante);

        reporte.setTotalEstudiantes(estudiantesCarrera.size());
        reporte.setEstudiantesPorSemestre1(contarPorSemestre(estudiantesCarrera, 1));
        reporte.setEstudiantesPorSemestre2(contarPorSemestre(estudiantesCarrera, 2));
        reporte.setEstudiantesPorSemestre3(contarPorSemestre(estudiantesCarrera, 3));
        reporte.setEstudiantesPorSemestre4(contarPorSemestre(estudiantesCarrera, 4));
        reporte.setEstudiantesPorSemestre5(contarPorSemestre(estudiantesCarrera, 5));
        reporte.setEstudiantesPorSemestre6(contarPorSemestre(estudiantesCarrera, 6));
        reporte.setEstudiantesPorSemestre7(contarPorSemestre(estudiantesCarrera, 7));
        reporte.setEstudiantesPorSemestre8(contarPorSemestre(estudiantesCarrera, 8));
        reporte.setEstudiantes(new ArrayList<>());

        return reporte;
    }

    public DtoReporteInscripciones reporteInscripcionesPorGestion(String codigoGestion) {
        Gestion gestion = repositorioGestion.buscarPorCodigo(codigoGestion).orElse(null);
        
        if (gestion == null)
            return null;

        DtoReporteInscripciones reporte = new DtoReporteInscripciones();
        reporte.setCodigoGestion(gestion.getCodigo());
        reporte.setNombreGestion(gestion.getNombre());

        List<Matricula> todasMatriculas = repositorioMatricula.getMatriculas();
        List<Matricula> matriculasGestion = new ArrayList<>();

        for (Matricula matricula : todasMatriculas)
            if (matricula.getParaleloMateria() != null &&
                matricula.getParaleloMateria().getGestion() != null &&
                matricula.getParaleloMateria().getGestion().getCodigo().equals(codigoGestion))
                matriculasGestion.add(matricula);

        reporte.setTotalInscripciones(matriculasGestion.size());

        int pendientes = 0;
        int aceptadas = 0;
        int rechazadas = 0;

        for (Matricula matricula : matriculasGestion) {
            if ("PENDIENTE".equals(matricula.getEstado()))
                ++pendientes;
            else if ("ACEPTADA".equals(matricula.getEstado()))
                ++aceptadas;
            else if ("RECHAZADA".equals(matricula.getEstado()))
                ++rechazadas;
        }

        reporte.setInscripcionesPendientes(pendientes);
        reporte.setInscripcionesAceptadas(aceptadas);
        reporte.setInscripcionesRechazadas(rechazadas);
        reporte.setInscripciones(new ArrayList<>());

        return reporte;
    }

    public DtoReporteRendimiento reporteRendimientoPorParalelo(String codigoParalelo) {
        ParaleloMateria paralelo = repositorioParalelo.buscarPorCodigo(codigoParalelo);
        
        if (paralelo == null)
            return null;

        DtoReporteRendimiento reporte = new DtoReporteRendimiento();
        reporte.setCodigoParalelo(paralelo.getCodigo());
        
        if (paralelo.getMateria() != null) {
            reporte.setCodigoMateria(paralelo.getMateria().getCodigo());
            reporte.setNombreMateria(paralelo.getMateria().getNombre());
        }
        
        if (paralelo.getDocente() != null)
            reporte.setNombreDocente(paralelo.getDocente().getNombre() + " " +
                                    paralelo.getDocente().getApellido());

        List<Estudiante> estudiantes = paralelo.getEstudiantes();
        int total = estudiantes != null ? estudiantes.size() : 0;
        
        reporte.setTotalEstudiantes(total);
        reporte.setPromedioGeneral(0.0);
        reporte.setAprobados(0);
        reporte.setReprobados(0);
        reporte.setSinCalificar(total);
        reporte.setEstudiantes(new ArrayList<>());

        return reporte;
    }

    public List<String> listarReportesDisponibles() {
        List<String> reportes = new ArrayList<>();
        reportes.add("estudiantes-por-carrera");
        reportes.add("inscripciones-por-gestion");
        reportes.add("rendimiento-por-paralelo");
        reportes.add("estadisticas-generales");
        return reportes;
    }

    private int contarPorSemestre(List<Estudiante> estudiantes, int semestre) {
        int contador = 0;

        for (Estudiante estudiante : estudiantes)
            if (estudiante.getSemestre() == semestre)
                ++contador;

        return contador;
    }
}

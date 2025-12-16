package com.example.Server.servicios;
import com.example.Server.casts.CastReporteEstudiantes;
import com.example.Server.casts.CastReporteInscripciones;
import com.example.Server.casts.CastReporteRendimiento;
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
    @Autowired
    private CastReporteEstudiantes convertidorEstudiantes;
    @Autowired
    private CastReporteInscripciones convertidorInscripciones;
    @Autowired
    private CastReporteRendimiento convertidorRendimiento;

    public DtoReporteEstudiantesPorCarrera reporteEstudiantesPorCarrera(String codigoCarrera) {
        Carrera carrera = repositorioCarrera.buscarPorCodigo(codigoCarrera);
        
        if (carrera == null)
            return null;

        List<Estudiante> estudiantesCarrera = new ArrayList<>();

        for (Estudiante estudiante : repositorioEstudiante.getEstudiantes())
            if (estudiante.getCarrera() != null && estudiante.getCarrera().getCodigo().equals(codigoCarrera))
                estudiantesCarrera.add(estudiante);

        return convertidorEstudiantes.getDto(carrera, estudiantesCarrera);
    }

    public DtoReporteInscripciones reporteInscripcionesPorGestion(String codigoGestion) {
        Gestion gestion = repositorioGestion.buscarPorCodigo(codigoGestion).orElse(null);
        
        if (gestion == null)
            return null;

        List<Matricula> todasMatriculas = repositorioMatricula.getMatriculas();
        List<Matricula> matriculasGestion = new ArrayList<>();

        for (Matricula matricula : todasMatriculas)
            if (matricula.getParaleloMateria() != null &&
                matricula.getParaleloMateria().getGestion() != null &&
                matricula.getParaleloMateria().getGestion().getCodigo().equals(codigoGestion))
                matriculasGestion.add(matricula);

        return convertidorInscripciones.getDto(gestion, matriculasGestion);
    }

    public DtoReporteRendimiento reporteRendimientoPorParalelo(String codigoParalelo) {
        ParaleloMateria paralelo = repositorioParalelo.buscarPorCodigo(codigoParalelo);
        
        if (paralelo == null)
            return null;

        return convertidorRendimiento.getDto(paralelo);
    }

    public List<String> listarReportesDisponibles() {
        List<String> reportes = new ArrayList<>();
        reportes.add("estudiantes-por-carrera");
        reportes.add("inscripciones-por-gestion");
        reportes.add("rendimiento-por-paralelo");
        reportes.add("estadisticas-generales");
        return reportes;
    }


}

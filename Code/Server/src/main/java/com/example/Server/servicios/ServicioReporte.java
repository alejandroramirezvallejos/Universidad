package com.example.Server.servicios;
import com.example.Server.estrategias.reporte.ContextoReportes;
import com.example.Server.modelos.Carrera;
import com.example.Server.modelos.Gestion;
import com.example.Server.modelos.ParaleloMateria;
import com.example.Server.modelos.ReporteDeCarrera;
import com.example.Server.modelos.ReporteDeInscripciones;
import com.example.Server.modelos.ReporteDeRendimiento;
import com.example.Server.repositorios.RepositorioCarrera;
import com.example.Server.repositorios.RepositorioEstudiante;
import com.example.Server.repositorios.RepositorioGestion;
import com.example.Server.repositorios.RepositorioMatricula;
import com.example.Server.repositorios.RepositorioParaleloMateria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ServicioReporte {
    private final RepositorioEstudiante repositorioEstudiante;
    private final RepositorioCarrera repositorioCarrera;
    private final RepositorioMatricula repositorioMatricula;
    private final RepositorioGestion repositorioGestion;
    private final RepositorioParaleloMateria repositorioParalelo;
    private final ContextoReportes contexto;

    public Map<String, Object> getReporteEstudiantesPorCarrera(String codigoCarrera, String solicitante) {
        Carrera carrera = repositorioCarrera.buscarPorCodigo(codigoCarrera);

        if (carrera == null)
            return null;

        return contexto.generarReporte(ReporteDeCarrera.builder()
                .carrera(carrera)
                .estudiantes(repositorioEstudiante.getEstudiantes())
                .solicitante(solicitante)
                .build());
    }

    public Map<String, Object> getReporteInscripcionesPorGestion(String codigoGestion) {
        Gestion gestion = repositorioGestion.buscarPorCodigo(codigoGestion).orElse(null);

        if (gestion == null)
            return null;

        return contexto.generarReporte(new ReporteDeInscripciones(gestion, repositorioMatricula.getMatriculas()));
    }

    public Map<String, Object> getReporteRendimientoPorParalelo(String codigoParalelo, String solicitante) {
        ParaleloMateria paralelo = repositorioParalelo.buscarPorCodigo(codigoParalelo);

        if (paralelo == null)
            return null;

        return contexto.generarReporte(ReporteDeRendimiento.builder()
                .paralelo(paralelo)
                .solicitante(solicitante)
                .build());
    }

    public List<String> getReportesDisponibles() {
        List<String> reportes = new ArrayList<>();
        reportes.add("estudiantes-por-carrera");
        reportes.add("inscripciones-por-gestion");
        reportes.add("rendimiento-por-paralelo");
        reportes.add("estadisticas-generales");

        return reportes;
    }
}

package com.example.Server.servicios;
import com.example.Server.componentes.GeneradorReportes;
import com.example.Server.modelos.Carrera;
import com.example.Server.modelos.Gestion;
import com.example.Server.modelos.ParaleloMateria;
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
public class ServicioReportes {
    private final RepositorioEstudiante repositorioEstudiante;
    private final RepositorioCarrera repositorioCarrera;
    private final RepositorioMatricula repositorioMatricula;
    private final RepositorioGestion repositorioGestion;
    private final RepositorioParaleloMateria repositorioParalelo;
    private final GeneradorReportes generador;

    public Map<String, Object> getReporteEstudiantesPorCarrera(String codigoCarrera) {
        Carrera carrera = repositorioCarrera.buscarPorCodigo(codigoCarrera);

        if (carrera == null)
            return null;

        return generador.generarReporteCarrera(carrera, repositorioEstudiante.getEstudiantes());
    }

    public Map<String, Object> getReporteInscripcionesPorGestion(String codigoGestion) {
        Gestion gestion = repositorioGestion.buscarPorCodigo(codigoGestion).orElse(null);

        if (gestion == null)
            return null;

        return generador.generarReporteInscripciones(gestion, repositorioMatricula.getMatriculas());
    }

    public Map<String, Object> getReporteRendimientoPorParalelo(String codigoParalelo) {
        ParaleloMateria paralelo = repositorioParalelo.buscarPorCodigo(codigoParalelo);

        if (paralelo == null)
            return null;

        return generador.generarReporteRendimiento(paralelo);
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

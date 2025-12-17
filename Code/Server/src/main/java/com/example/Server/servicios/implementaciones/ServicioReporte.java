package com.example.Server.servicios.implementaciones;
import com.example.Server.estrategias.reporte.ContextoReporte;
import com.example.Server.modelos.abstracciones.ICarrera;
import com.example.Server.modelos.abstracciones.IGestion;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import com.example.Server.modelos.implementaciones.ParaleloMateria;
import com.example.Server.modelos.implementaciones.ReporteDeCarrera;
import com.example.Server.modelos.implementaciones.ReporteDeInscripciones;
import com.example.Server.modelos.implementaciones.ReporteDeRendimiento;
import com.example.Server.repositorios.abstracciones.IRepositorioCarrera;
import com.example.Server.repositorios.abstracciones.IRepositorioEstudiante;
import com.example.Server.repositorios.abstracciones.IRepositorioGestion;
import com.example.Server.repositorios.abstracciones.IRepositorioMatricula;
import com.example.Server.repositorios.abstracciones.IRepositorioParaleloMateria;
import com.example.Server.servicios.abstracciones.IServicioReporte;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ServicioReporte implements IServicioReporte {
    private final IRepositorioEstudiante repositorioEstudiante;
    private final IRepositorioCarrera repositorioCarrera;
    private final IRepositorioMatricula repositorioMatricula;
    private final IRepositorioGestion repositorioGestion;
    private final IRepositorioParaleloMateria repositorioParalelo;
    private final ContextoReporte contexto;

    @Override
    public Map<String, Object> getReporteEstudiantesPorCarrera(String codigoCarrera, String solicitante) {
        ICarrera carrera = repositorioCarrera.buscar(codigoCarrera);

        if (carrera == null)
            throw new RuntimeException("Carrera no encontrada");

        return contexto.generarReporte(ReporteDeCarrera.builder()
                .carrera(carrera)
                .estudiantes(repositorioEstudiante.getEstudiantes())
                .solicitante(solicitante)
                .build());
    }

    @Override
    public Map<String, Object> getReporteInscripcionesPorGestion(String codigoGestion) {
        IGestion gestion = repositorioGestion.buscarPorCodigo(codigoGestion);

        if (gestion == null)
            throw new RuntimeException("Gestión no encontrada");

        return contexto.generarReporte(ReporteDeInscripciones.builder()
                .gestion(gestion)
                .matriculas(repositorioMatricula.getMatriculas())
                .build());
    }

    @Override
    public Map<String, Object> getReporteRendimientoPorParalelo(String codigoParalelo, String solicitante) {
        IParaleloMateria paralelo = repositorioParalelo.buscar(codigoParalelo);

        if (paralelo == null)
            throw new RuntimeException("Paralelo no encontrado");

        return contexto.generarReporte(ReporteDeRendimiento.builder()
                .paralelo((ParaleloMateria) paralelo)
                .solicitante(solicitante)
                .build());
    }

    @Override
    public List<String> getReportesDisponibles() {
        List<String> reportes = new ArrayList<>();
        reportes.add("estudiantes-por-carrera");
        reportes.add("inscripciones-por-gestion");
        reportes.add("rendimiento-por-paralelo");
        reportes.add("estadisticas-generales");

        return reportes;
    }
}

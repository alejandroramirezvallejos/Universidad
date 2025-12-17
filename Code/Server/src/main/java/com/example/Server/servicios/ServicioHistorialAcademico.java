package com.example.Server.servicios;
import com.example.Server.estrategias.calificacion.CalcularCalificacionPromedio;
import com.example.Server.estrategias.credito.CalcularCreditosAprobados;
import com.example.Server.estrategias.credito.CalcularCreditosTotales;
import com.example.Server.modelos.ActaEstudiante;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.HistorialAcademico;
import com.example.Server.repositorios.RepositorioActaEstudiante;
import com.example.Server.repositorios.RepositorioHistorialAcademico;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ServicioHistorialAcademico {
    private final RepositorioActaEstudiante repositorioActa;
    private final RepositorioHistorialAcademico repositorio;
    private final CalcularCalificacionPromedio calcularCalificacionPromedio;
    private final CalcularCreditosAprobados calcularCreditosAprobados;
    private final CalcularCreditosTotales calcularCreditosTotales;

    public HistorialAcademico crear(Estudiante estudiante) {
        List<ActaEstudiante> actas = getActasEstudiante(estudiante);
        double promedio = calcularCalificacionPromedio.calcular(actas);
        int aprobados = (int) calcularCreditosAprobados.calcular(actas);
        int totales = (int) calcularCreditosTotales.calcular(actas);
        HistorialAcademico historial = construir(estudiante, actas, promedio, aprobados, totales);
        return repositorio.guardar(historial);
    }


    private List<ActaEstudiante> getActasEstudiante(Estudiante estudiante) {
        return repositorioActa.buscarPorEstudiante(estudiante.getCodigo());
    }

    private HistorialAcademico construir(Estudiante estudiante, List<ActaEstudiante> actas,
                                         double promedio, int aprobados, int totales) {
        HistorialAcademico historial = new HistorialAcademico();
        historial.setEstudiante(estudiante);
        historial.setActas(actas);
        historial.setPromedioGeneral(promedio);
        historial.setCreditosAprobados(aprobados);
        historial.setCreditosTotales(totales);
        return historial;
    }

    public List<HistorialAcademico> getHistoriales() {
        return repositorio.getHistoriales();
    }

    public void eliminar(HistorialAcademico historial) {
        repositorio.eliminar(historial);
    }

    public HistorialAcademico getHistorialPorEstudiante(String estudianteCodigo) {
        for (HistorialAcademico historial : getHistoriales())
            if (historial.getEstudiante().getCodigo().equals(estudianteCodigo))
                return historial;

        throw new RuntimeException("Historial no encontrado");
    }

    public Double calcularPromedioGeneral(String estudianteCodigo) {
        try {
            HistorialAcademico historial = getHistorialPorEstudiante(estudianteCodigo);
            return historial.getPromedioGeneral();
        } catch (RuntimeException e) {
            return 0.0;
        }
    }

    public Map<String, Object> getPromedioMap(String estudianteCodigo) {
        Double promedio = calcularPromedioGeneral(estudianteCodigo);
        return Map.of(
            "estudianteCodigo", estudianteCodigo,
            "promedio", promedio,
            "totalMaterias", 0
        );
    }
}

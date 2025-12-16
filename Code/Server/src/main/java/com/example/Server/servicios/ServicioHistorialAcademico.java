package com.example.Server.servicios;
import com.example.Server.estrategias.historial.CalcularCreditosAprobados;
import com.example.Server.estrategias.historial.CalcularCreditosTotales;
import com.example.Server.estrategias.historial.CalcularPromedio;
import com.example.Server.estrategias.historial.ContextoCalculoHistorial;
import com.example.Server.estrategias.historial.IEstrategiaCalculoHistorial;
import com.example.Server.modelos.ActaEstudiante;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.HistorialAcademico;
import com.example.Server.repositorios.RepositorioActaEstudiante;
import com.example.Server.repositorios.RepositorioHistorialAcademico;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioHistorialAcademico {
    private final RepositorioActaEstudiante repositorioActa;
    private final RepositorioHistorialAcademico repositorio;
    private final ContextoCalculoHistorial contexto;
    private final CalcularPromedio calcularPromedio;
    private final CalcularCreditosAprobados calcularCreditosAprobados;
    private final CalcularCreditosTotales calcularCreditosTotales;

    public HistorialAcademico crear(Estudiante estudiante) {
        List<ActaEstudiante> actas = getActasEstudiante(estudiante);
        double promedio = calcular(calcularPromedio, actas);
        int aprobados = (int) calcular(calcularCreditosAprobados, actas);
        int totales = (int) calcular(calcularCreditosTotales, actas);
        HistorialAcademico historial = construir(estudiante, actas, promedio, aprobados, totales);
        return repositorio.guardar(historial);
    }

    private double calcular(IEstrategiaCalculoHistorial estrategia, List<ActaEstudiante> actas) {
        contexto.setEstrategia(estrategia);
        return contexto.ejecutar(actas);
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

        return null;
    }

    public Double calcularPromedioGeneral(String estudianteCodigo) {
        HistorialAcademico historial = getHistorialPorEstudiante(estudianteCodigo);
        return historial != null ? historial.getPromedioGeneral() : 0.0;
    }
}

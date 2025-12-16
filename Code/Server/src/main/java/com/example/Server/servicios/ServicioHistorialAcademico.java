package com.example.Server.servicios;
import com.example.Server.componentes.CalculadoraHistorial;
import com.example.Server.modelos.ActaEstudiante;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.HistorialAcademico;
import com.example.Server.repositorios.RepositorioActaEstudiante;
import com.example.Server.repositorios.RepositorioHistorialAcademico;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioHistorialAcademico {
    private final RepositorioActaEstudiante repositorioActa;
    private final RepositorioHistorialAcademico repositorio;
    private final CalculadoraHistorial calculadora;

    public HistorialAcademico setHistorial(Estudiante estudiante) {
        List<ActaEstudiante> actas = getActasEstudiante(estudiante);
        double promedio = calculadora.calcularPromedio(actas);
        int aprobados = calculadora.calcularCreditosAprobados(actas);
        int totales = calculadora.calcularCreditosTotales(actas);
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
        for (HistorialAcademico historial : repositorio.getHistoriales())
            if (historial.getEstudiante() != null && historial.getEstudiante().getCodigo().equals(estudianteCodigo))
                return historial;

        return null;
    }

    public Double getPromedioGeneral(String estudianteCodigo) {
        List<ActaEstudiante> actas = new ArrayList<>();

        for (ActaEstudiante acta : repositorioActa.getActas())
            if (acta.getEstudiante() != null && acta.getEstudiante().getCodigo().equals(estudianteCodigo))
                actas.add(acta);

        if (actas.isEmpty())
            return 0.0;

        return calculadora.calcularPromedio(actas);
    }
}

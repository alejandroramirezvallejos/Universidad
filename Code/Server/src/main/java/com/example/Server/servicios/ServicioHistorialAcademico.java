package com.example.Server.servicios;
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

    public HistorialAcademico crear(Estudiante estudiante) {
        List<ActaEstudiante> actas = getActasEstudiante(estudiante);
        double promedio = calcularPromedio(actas);
        int aprobados = calcularCreditosAprobados(actas);
        int totales = calcularCreditosTotales(actas);

        HistorialAcademico historial = construir(estudiante, actas, promedio, aprobados, totales);
        return repositorio.guardar(historial);
    }

    private List<ActaEstudiante> getActasEstudiante(Estudiante estudiante) {
        List<ActaEstudiante> todas = repositorioActa.getActas();
        List<ActaEstudiante> resultado = new ArrayList<>();

        for (ActaEstudiante acta : todas)
            if (acta.getEstudiante().getCodigo().equals(estudiante.getCodigo()))
                resultado.add(acta);

        return resultado;
    }

    private double calcularPromedio(List<ActaEstudiante> actas) {
        if (actas.isEmpty())
            return 0.0;

        double suma = 0.0;

        for (ActaEstudiante acta : actas)
            if (acta.isAprobado())
                suma += acta.getCalificacionFinal();

        return suma / actas.size();
    }

    private int calcularCreditosAprobados(List<ActaEstudiante> actas) {
        int creditos = 0;

        for (ActaEstudiante acta : actas)
            if (acta.isAprobado())
                creditos += acta.getParaleloMateria().getMateria().getCreditos();

        return creditos;
    }

    private int calcularCreditosTotales(List<ActaEstudiante> actas) {
        int creditos = 0;

        for (ActaEstudiante acta : actas)
            creditos += acta.getParaleloMateria().getMateria().getCreditos();

        return creditos;
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

    public HistorialAcademico obtenerPorEstudiante(String estudianteCodigo) {
        List<HistorialAcademico> historiales = repositorio.getHistoriales();
        return historiales.stream()
                .filter(h -> h.getEstudiante() != null &&
                        h.getEstudiante().getCodigo().equals(estudianteCodigo))
                .findFirst()
                .orElse(null);
    }

    public Double calcularPromedioGeneral(String estudianteCodigo) {
        List<ActaEstudiante> actas = repositorioActa.getActas().stream()
                .filter(a -> a.getEstudiante() != null &&
                        a.getEstudiante().getCodigo().equals(estudianteCodigo))
                .toList();
        
        if (actas.isEmpty()) {
            return 0.0;
        }
        
        return calcularPromedio(actas);
    }
}




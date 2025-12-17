package com.example.Server.servicios.implementaciones;
import com.example.Server.modelos.abstracciones.IActaEstudiante;
import com.example.Server.modelos.abstracciones.ICalificacion;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IHistorialAcademico;
import com.example.Server.modelos.implementaciones.Calificacion;
import com.example.Server.modelos.implementaciones.HistorialAcademico;
import com.example.Server.repositorios.abstracciones.IRepositorioActaEstudiante;
import com.example.Server.repositorios.abstracciones.IRepositorioHistorialAcademico;
import com.example.Server.estrategias.calificacion.CalcularCalificacionPromedio;
import com.example.Server.estrategias.credito.CalcularCreditosAprobados;
import com.example.Server.estrategias.credito.CalcularCreditosTotales;
import com.example.Server.servicios.abstracciones.IServicioHistorialAcademico;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioHistorialAcademico implements IServicioHistorialAcademico {
    private final IRepositorioActaEstudiante repositorioActa;
    private final IRepositorioHistorialAcademico repositorio;
    private final CalcularCalificacionPromedio calculadorPromedio;
    private final CalcularCreditosAprobados calculadorCreditosAprobados;
    private final CalcularCreditosTotales calculadorCreditosTotales;

    @Override
    public IHistorialAcademico crear(IEstudiante estudiante) {
        List<IActaEstudiante> actas = repositorioActa.buscar(estudiante.getCodigo());
        double promedio = calculadorPromedio.calcular(actas);
        int aprobados = (int) calculadorCreditosAprobados.calcular(actas);
        int totales = (int) calculadorCreditosTotales.calcular(actas);

        HistorialAcademico historial = new HistorialAcademico();
        historial.setEstudiante(estudiante);
        historial.setActas(actas);
        historial.setPromedioGeneral(promedio);
        historial.setCreditosAprobados(aprobados);
        historial.setCreditosTotales(totales);

        return repositorio.guardar(historial);
    }

    @Override
    public List<IHistorialAcademico> getHistoriales() {
        return repositorio.getHistoriales();
    }

    @Override
    public void eliminar(IHistorialAcademico historial) {
        repositorio.eliminar(historial);
    }

    @Override
    public IHistorialAcademico getHistorialPorEstudiante(String estudianteCodigo) {
        IHistorialAcademico historial = repositorio.buscar(estudianteCodigo);

        if (historial == null)
            throw new RuntimeException("Historial no encontrado");

        return historial;
    }

    @Override
    public Double calcularPromedioGeneral(String estudianteCodigo) {
        IHistorialAcademico historial = repositorio.buscar(estudianteCodigo);

        if (historial == null)
            return 0.0;

        return historial.getPromedioGeneral();
    }

    @Override
    public ICalificacion getPromedioEstudiante(String estudianteCodigo) {
        Double promedio = calcularPromedioGeneral(estudianteCodigo);
        IHistorialAcademico historial = repositorio.buscar(estudianteCodigo);

        Calificacion calificacion = new Calificacion();
        calificacion.setValor(promedio);
        calificacion.setObservaciones("Promedio General");

        if (historial != null)
            calificacion.setEstudiante(historial.getEstudiante());

        return calificacion;
    }
}

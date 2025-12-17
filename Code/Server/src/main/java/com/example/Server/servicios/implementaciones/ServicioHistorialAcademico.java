package com.example.Server.servicios.implementaciones;
import com.example.Server.modelos.abstracciones.IActaEstudiante;
import com.example.Server.modelos.abstracciones.ICalificacion;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IHistorialAcademico;
import com.example.Server.modelos.implementaciones.Calificacion;
import com.example.Server.modelos.implementaciones.HistorialAcademico;
import com.example.Server.repositorios.abstracciones.IRepositorioActaEstudiante;
import com.example.Server.repositorios.abstracciones.IRepositorioHistorialAcademico;
import com.example.Server.estrategias.actas.ContextoCalculoPromedioActas;
import com.example.Server.estrategias.actas.IEstrategiaCalculoPromedioActas;
import com.example.Server.estrategias.credito.ContextoCalculoCredito;
import com.example.Server.estrategias.credito.IEstrategiaCalculoCredito;
import com.example.Server.servicios.abstracciones.IServicioHistorialAcademico;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServicioHistorialAcademico implements IServicioHistorialAcademico {
    private final IRepositorioActaEstudiante repositorioActa;
    private final IRepositorioHistorialAcademico repositorio;
    private final ContextoCalculoPromedioActas contextoPromedio;
    private final ContextoCalculoCredito contextoCredito;
    private final IEstrategiaCalculoPromedioActas estrategiaPromedio;
    private final IEstrategiaCalculoCredito estrategiaCreditosAprobados;
    private final IEstrategiaCalculoCredito estrategiaCreditosTotales;

    public ServicioHistorialAcademico(
            IRepositorioActaEstudiante repositorioActa,
            IRepositorioHistorialAcademico repositorio,
            ContextoCalculoPromedioActas contextoPromedio,
            ContextoCalculoCredito contextoCredito,
            IEstrategiaCalculoPromedioActas estrategiaPromedio,
            @Qualifier("calcularCreditosAprobados") IEstrategiaCalculoCredito estrategiaCreditosAprobados,
            @Qualifier("calcularCreditosTotales") IEstrategiaCalculoCredito estrategiaCreditosTotales) {
        this.repositorioActa = repositorioActa;
        this.repositorio = repositorio;
        this.contextoPromedio = contextoPromedio;
        this.contextoCredito = contextoCredito;
        this.estrategiaPromedio = estrategiaPromedio;
        this.estrategiaCreditosAprobados = estrategiaCreditosAprobados;
        this.estrategiaCreditosTotales = estrategiaCreditosTotales;
    }

    @Override
    public IHistorialAcademico crear(IEstudiante estudiante) {
        List<IActaEstudiante> actas = repositorioActa.buscar(estudiante.getCodigo());

        contextoPromedio.setEstrategia(estrategiaPromedio);
        double promedio = contextoPromedio.calcular(actas);

        contextoCredito.setEstrategia(estrategiaCreditosAprobados);
        int aprobados = (int) contextoCredito.calcular(actas);

        contextoCredito.setEstrategia(estrategiaCreditosTotales);
        int totales = (int) contextoCredito.calcular(actas);

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

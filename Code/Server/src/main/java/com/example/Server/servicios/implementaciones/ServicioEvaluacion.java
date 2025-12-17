package com.example.Server.servicios.implementaciones;
import com.example.Server.modelos.abstracciones.ICalificacion;
import com.example.Server.modelos.abstracciones.IEvaluacion;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import com.example.Server.modelos.implementaciones.Evaluacion;
import com.example.Server.repositorios.abstracciones.IRepositorioEvaluacion;
import com.example.Server.servicios.abstracciones.IServicioEvaluacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioEvaluacion implements IServicioEvaluacion {
    private final IRepositorioEvaluacion repositorio;

    @Override
    public IEvaluacion crear(IParaleloMateria paralelo, String nombre, Double porcentaje) {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setParaleloMateria(paralelo);
        evaluacion.setNombre(nombre);
        evaluacion.setPorcentaje(porcentaje);
        evaluacion.setCalificaciones(new ArrayList<>());
        return repositorio.guardar(evaluacion);
    }

    @Override
    public void agregar(IEvaluacion evaluacion, ICalificacion calificacion) {
        evaluacion.getCalificaciones().add(calificacion);
        repositorio.guardar(evaluacion);
    }

    @Override
    public List<IEvaluacion> getEvaluaciones() {
        return repositorio.getEvaluaciones();
    }

    @Override
    public void eliminar(IEvaluacion evaluacion) {
        repositorio.eliminar(evaluacion);
    }

    @Override
    public List<IEvaluacion> getEvaluacionesPorParalelo(String paraleloCodigo) {
        return repositorio.buscarPorParalelo(paraleloCodigo);
    }

    @Override
    public List<ICalificacion> getCalificacionesEstudiante(String estudianteCodigo) {
        List<ICalificacion> calificaciones = new ArrayList<>();

        for (IEvaluacion evaluacion : repositorio.getEvaluaciones())
            for (ICalificacion calificacion : evaluacion.getCalificaciones())
                if (calificacion.getEstudiante() != null && calificacion.getEstudiante().getCodigo().equals(estudianteCodigo)) {
                    calificacion.setEvaluacion(evaluacion);
                    calificaciones.add(calificacion);
                }

        return calificaciones;
    }
}

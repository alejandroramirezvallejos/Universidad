package com.example.Server.servicios.implementaciones;

import com.example.Server.modelos.abstracciones.ICalificacion;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IEvaluacion;
import com.example.Server.repositorios.abstracciones.IRepositorioCalificacion;
import com.example.Server.repositorios.abstracciones.IRepositorioEstudiante;
import com.example.Server.servicios.abstracciones.IServicioCalificacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioCalificacion implements IServicioCalificacion {
    private final IRepositorioCalificacion repositorio;
    private final IRepositorioEstudiante repositorioEstudiante;

    @Override
    public ICalificacion crear(ICalificacion calificacion) {
        return repositorio.guardar(calificacion);
    }

    @Override
    public List<ICalificacion> getCalificaciones() {
        return repositorio.getCalificaciones();
    }

    @Override
    public List<ICalificacion> getCalificacionesPorEstudiante(String estudianteCodigo) {
        IEstudiante estudiante = repositorioEstudiante.buscarPorCodigo(estudianteCodigo);
        if (estudiante == null)
            return new ArrayList<>();
        return repositorio.buscarPorEstudiante(estudianteCodigo);
    }

    @Override
    public double getCalificacionesEnEvaluacion(IEstudiante estudiante, IEvaluacion evaluacion) {
        if (evaluacion.getCalificaciones() == null)
            return 0.0;

        for (ICalificacion calificacion : evaluacion.getCalificaciones())
            if (calificacion.getEstudiante().getCodigo().equals(estudiante.getCodigo()))
                return calificacion.getValor();

        return 0.0;
    }

    @Override
    public void eliminar(ICalificacion calificacion) {
        repositorio.eliminar(calificacion);
    }
}

package com.example.Server.servicios.abstracciones;

import com.example.Server.modelos.abstracciones.ICalificacion;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IEvaluacion;
import java.util.List;

public interface IServicioCalificacion {
    ICalificacion crear(ICalificacion calificacion);
    List<ICalificacion> getCalificaciones();
    List<ICalificacion> getCalificacionesPorEstudiante(String estudianteCodigo);
    double getCalificacionesEnEvaluacion(IEstudiante estudiante, IEvaluacion evaluacion);
    void eliminar(ICalificacion calificacion);
}


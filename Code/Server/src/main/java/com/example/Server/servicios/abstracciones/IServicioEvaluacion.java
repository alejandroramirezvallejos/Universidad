package com.example.Server.servicios.abstracciones;

import com.example.Server.modelos.abstracciones.ICalificacion;
import com.example.Server.modelos.abstracciones.IEvaluacion;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import java.util.List;

public interface IServicioEvaluacion {
    IEvaluacion crear(IParaleloMateria paralelo, String nombre, Double porcentaje);
    void agregar(IEvaluacion evaluacion, ICalificacion calificacion);
    List<IEvaluacion> getEvaluaciones();
    void eliminar(IEvaluacion evaluacion);
    List<IEvaluacion> getEvaluacionesPorParalelo(String paraleloCodigo);
    List<ICalificacion> getCalificacionesEstudiante(String estudianteCodigo);
}


package com.example.Server.modelos.abstracciones;

public interface ICalificacion {
    Double getValor();
    void setValor(Double valor);
    String getObservaciones();
    void setObservaciones(String observaciones);
    IEstudiante getEstudiante();
    void setEstudiante(IEstudiante estudiante);
    IEvaluacion getEvaluacion();
    void setEvaluacion(IEvaluacion evaluacion);
}

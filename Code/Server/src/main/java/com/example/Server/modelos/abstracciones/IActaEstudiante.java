package com.example.Server.modelos.abstracciones;

import java.util.List;

public interface IActaEstudiante {
    IEstudiante getEstudiante();
    void setEstudiante(IEstudiante estudiante);
    IParaleloMateria getParaleloMateria();
    void setParaleloMateria(IParaleloMateria paraleloMateria);
    List<ICalificacion> getCalificaciones();
    void setCalificaciones(List<ICalificacion> calificaciones);
    Double getCalificacionFinal();
    void setCalificacionFinal(Double calificacionFinal);
    boolean isAprobado();
    void setAprobado(boolean aprobado);
}

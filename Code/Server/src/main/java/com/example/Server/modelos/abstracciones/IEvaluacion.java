package com.example.Server.modelos.abstracciones;

import java.util.List;

public interface IEvaluacion {
    String getCodigo();
    void setCodigo(String codigo);
    String getNombre();
    void setNombre(String nombre);
    Double getPorcentaje();
    void setPorcentaje(Double porcentaje);
    IParaleloMateria getParaleloMateria();
    void setParaleloMateria(IParaleloMateria paraleloMateria);
    List<ICalificacion> getCalificaciones();
    void setCalificaciones(List<ICalificacion> calificaciones);
}

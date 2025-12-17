package com.example.Server.modelos.abstracciones;
import com.example.Server.modelos.implementaciones.Carrera;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Carrera.class)
public interface ICarrera {
    String getCodigo();
    void setCodigo(String codigo);
    String getNombre();
    void setNombre(String nombre);
}

package com.example.Server.modelos.abstracciones;
import com.example.Server.modelos.implementaciones.Carrera;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Interfaz simplificada para Carrera (solo código y nombre)
 * Esto evita referencias circulares en la deserialización JSON
 */
@JsonDeserialize(as = Carrera.class)
public interface ICarrera {
    String getCodigo();
    void setCodigo(String codigo);
    String getNombre();
    void setNombre(String nombre);
}

package com.example.Server.modelos.abstracciones;

public interface IAula {
    Boolean isDisponible();
    void setDisponible(Boolean disponible); 
    Integer getCapacidad();
    void setCapacidad(Integer capacidad);
    String getEdificio();
    void setEdificio(String edificio);
    String getCodigo();
    void setCodigo(String codigo);
}


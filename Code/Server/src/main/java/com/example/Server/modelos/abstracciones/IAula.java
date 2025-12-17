package com.example.Server.modelos.abstracciones;

public interface IAula {
    boolean isDisponible();
    void setDisponible(boolean disponible);
    Integer getCapacidad();
    void setCapacidad(Integer capacidad);
    String getEdificio();
    void setEdificio(String edificio);
    String getCodigo();
    void setCodigo(String codigo);
}


package com.example.Server.modelos.abstracciones;

public interface IAula {
    Boolean isDisponible();  // Cambiado de boolean a Boolean
    void setDisponible(Boolean disponible);  // Cambiado de boolean a Boolean
    Integer getCapacidad();
    void setCapacidad(Integer capacidad);
    String getEdificio();
    void setEdificio(String edificio);
    String getCodigo();
    void setCodigo(String codigo);
}


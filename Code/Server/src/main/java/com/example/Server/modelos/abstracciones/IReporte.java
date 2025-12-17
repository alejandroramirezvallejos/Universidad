package com.example.Server.modelos.abstracciones;
import java.util.Date;

public interface IReporte {
    Date getFechaGeneracion();
    void setFechaGeneracion(Date fechaGeneracion);
    String getSolicitante();
    void setSolicitante(String solicitante);
}


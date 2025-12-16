package com.example.Server.alertas;
import com.example.Server.modelos.NotificacionEvento;

public interface IObservador {
    void actualizar(NotificacionEvento evento);
}


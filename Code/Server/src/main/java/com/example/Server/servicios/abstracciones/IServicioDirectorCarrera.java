package com.example.Server.servicios.abstracciones;
import com.example.Server.modelos.abstracciones.IDirectorCarrera;
import java.util.List;

public interface IServicioDirectorCarrera {
    IDirectorCarrera crear(IDirectorCarrera director);
    List<IDirectorCarrera> getDirectores();
    void eliminar(IDirectorCarrera director);
}


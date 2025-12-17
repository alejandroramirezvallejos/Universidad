package com.example.Server.servicios.abstracciones;

import com.example.Server.modelos.abstracciones.ICarrera;
import java.util.List;

public interface IServicioCarrera {
    ICarrera crear(ICarrera carrera);
    List<ICarrera> getCarreras();
    void eliminar(ICarrera carrera);
}


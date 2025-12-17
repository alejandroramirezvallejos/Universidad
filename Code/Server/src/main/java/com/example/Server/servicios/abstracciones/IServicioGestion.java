package com.example.Server.servicios.abstracciones;

import com.example.Server.modelos.abstracciones.IGestion;
import java.util.List;

public interface IServicioGestion {
    IGestion crear(IGestion gestion);
    List<IGestion> getGestiones();
    IGestion getGestionPorCodigo(String codigo);
    IGestion getGestionActual();
    void eliminar(IGestion gestion);
}


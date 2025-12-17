package com.example.Server.repositorios.abstracciones;
import com.example.Server.modelos.abstracciones.IGestion;
import java.util.List;

public interface IRepositorioGestion {
    IGestion guardar(IGestion gestion);
    List<IGestion> getGestiones();
    void eliminar(IGestion gestion);
    IGestion buscarPorCodigo(String codigo);
    IGestion buscarGestionActual();
}


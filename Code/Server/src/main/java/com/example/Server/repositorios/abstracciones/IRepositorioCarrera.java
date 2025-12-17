package com.example.Server.repositorios.abstracciones;
import com.example.Server.modelos.abstracciones.ICarrera;
import java.util.List;

public interface IRepositorioCarrera {
    ICarrera guardar(ICarrera carrera);
    List<ICarrera> getCarreras();
    void eliminar(ICarrera carrera);
    ICarrera buscar(String codigo);
}


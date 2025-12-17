package com.example.Server.repositorios.abstracciones;

import com.example.Server.modelos.abstracciones.IDirectorCarrera;
import java.util.List;

public interface IRepositorioDirectorCarrera {
    IDirectorCarrera guardar(IDirectorCarrera director);
    List<IDirectorCarrera> getDirectores();
    void eliminar(IDirectorCarrera director);
    IDirectorCarrera buscarPorCodigo(String codigo);
    IDirectorCarrera buscarPorEmail(String email);
}


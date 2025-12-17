package com.example.Server.repositorios.abstracciones;

import com.example.Server.modelos.abstracciones.IDocente;
import java.util.List;

public interface IRepositorioDocente {
    IDocente guardar(IDocente docente);
    List<IDocente> getDocentes();
    void eliminar(IDocente docente);
    IDocente buscarPorCodigo(String codigo);
    IDocente buscarPorEmail(String email);
}


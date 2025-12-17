package com.example.Server.servicios.abstracciones;

import com.example.Server.modelos.abstracciones.IDocente;
import java.util.List;

public interface IServicioDocente {
    IDocente crear(IDocente docente);
    List<IDocente> getDocentes();
    void eliminar(IDocente docente);
    List<IDocente> getDocentesActivos();
    IDocente buscarPorCodigo(String codigo);
}


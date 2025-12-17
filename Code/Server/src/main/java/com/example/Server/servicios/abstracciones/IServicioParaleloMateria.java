package com.example.Server.servicios.abstracciones;

import com.example.Server.modelos.abstracciones.IParaleloMateria;
import java.util.List;

public interface IServicioParaleloMateria {
    IParaleloMateria crear(IParaleloMateria paralelo);
    List<IParaleloMateria> getParalelos();
    void eliminar(IParaleloMateria paralelo);
    IParaleloMateria getParaleloPorCodigo(String codigo);
    List<IParaleloMateria> getParalelosPorDocente(String docenteCodigo);
    List<IParaleloMateria> getParalelosPorMateria(String materiaCodigo);
    IParaleloMateria actualizar(String codigo, IParaleloMateria paraleloDto);
}


package com.example.Server.repositorios.abstracciones;

import com.example.Server.modelos.abstracciones.IParaleloMateria;
import java.util.List;

public interface IRepositorioParaleloMateria {
    IParaleloMateria guardar(IParaleloMateria paralelo);
    List<IParaleloMateria> getParalelos();
    void eliminar(IParaleloMateria paralelo);
    IParaleloMateria buscarPorCodigo(String codigo);
}


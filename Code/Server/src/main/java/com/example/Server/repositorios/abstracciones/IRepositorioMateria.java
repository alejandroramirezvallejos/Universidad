package com.example.Server.repositorios.abstracciones;
import com.example.Server.modelos.abstracciones.IMateria;
import java.util.List;

public interface IRepositorioMateria {
    IMateria guardar(IMateria materia);
    List<IMateria> getMaterias();
    void eliminar(IMateria materia);
    IMateria buscar(String codigo);
}


package com.example.Server.servicios.abstracciones;

import com.example.Server.modelos.abstracciones.ICarrera;
import com.example.Server.modelos.abstracciones.IMateria;
import java.util.List;

public interface IServicioMateria {
    IMateria agregar(IMateria materia, ICarrera carrera);
    List<IMateria> getMaterias();
    IMateria crear(IMateria materia);
    void eliminar(IMateria materia);
    IMateria getMateriaPorCodigo(String codigo);
    IMateria actualizar(String codigo, IMateria materiaDto);
    IMateria cambiarEstado(String codigo);
}


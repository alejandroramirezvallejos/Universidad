package com.example.Server.servicios.abstracciones;
import com.example.Server.modelos.abstracciones.IMateria;
import com.example.Server.modelos.implementaciones.Carrera;
import java.util.List;

public interface IServicioMateria {
    IMateria agregar(IMateria materia, Carrera carrera);
    List<IMateria> getMaterias();
    IMateria crear(IMateria materia);
    void eliminar(IMateria materia);
    IMateria getMateria(String codigo);
    IMateria actualizar(String codigo, IMateria materiaDto);
    IMateria setEstado(String codigo);
}


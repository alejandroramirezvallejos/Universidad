package com.example.Server.modelos.abstracciones;
import java.util.List;

public interface IDocente extends IUsuario {
    String getDepartamento();
    void setDepartamento(String departamento);
    String getEspecialidad();
    void setEspecialidad(String especialidad);
    boolean isActivo();
    void setActivo(boolean activo);
    List<IParaleloMateria> getParaleloMaterias();
    void setParaleloMaterias(List<IParaleloMateria> paraleloMaterias);
}

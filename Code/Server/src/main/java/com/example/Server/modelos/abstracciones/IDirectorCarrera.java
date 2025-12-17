package com.example.Server.modelos.abstracciones;
import com.example.Server.modelos.implementaciones.Carrera;

public interface IDirectorCarrera extends IUsuario {
    String getDepartamento();
    void setDepartamento(String departamento);
    Carrera getCarrera();
    void setCarrera(Carrera carrera);
}


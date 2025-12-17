package com.example.Server.modelos.abstracciones;

public interface IDirectorCarrera extends IUsuario {
    String getDepartamento();
    void setDepartamento(String departamento);
    ICarrera getCarrera();
    void setCarrera(ICarrera carrera);
}


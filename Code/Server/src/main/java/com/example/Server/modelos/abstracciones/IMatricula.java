package com.example.Server.modelos.abstracciones;

public interface IMatricula {
    String getEstado();
    void setEstado(String estado);
    IParaleloMateria getParaleloMateria();
    void setParaleloMateria(IParaleloMateria paraleloMateria);
    IEstudiante getEstudiante();
    void setEstudiante(IEstudiante estudiante);
}

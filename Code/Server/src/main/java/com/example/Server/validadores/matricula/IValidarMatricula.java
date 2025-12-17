package com.example.Server.validadores.matricula;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IParaleloMateria;

public interface IValidarMatricula {
    String validar(IEstudiante estudiante, IParaleloMateria paraleloMateria);
}

package com.example.Server.validadores.matricula;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.ParaleloMateria;

public interface IValidarMatricula {
    String validar(Estudiante estudiante, ParaleloMateria paraleloMateria);
}

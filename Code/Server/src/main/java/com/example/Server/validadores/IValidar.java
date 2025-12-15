package com.example.Server.validadores;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.ParaleloMateria;

public interface IValidar {
    IValidar setSiguiente(IValidar siguiente);
    String validar(Estudiante estudiante, ParaleloMateria paraleloMateria);
}

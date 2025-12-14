package com.example.Server.validadores;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.ParaleloMateria;

public interface IValidador {
    void setSiguiente(IValidador siguiente);
    String validar(Estudiante estudiante, ParaleloMateria paraleloMateria);
}


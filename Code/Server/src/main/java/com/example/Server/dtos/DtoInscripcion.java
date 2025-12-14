package com.example.Server.dtos;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.ParaleloMateria;
import lombok.Data;

@Data
public class DtoInscripcion {
    private Estudiante estudiante;
    private ParaleloMateria paraleloMateria;
}


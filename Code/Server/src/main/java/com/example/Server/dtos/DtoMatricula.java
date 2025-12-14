package com.example.Server.dtos;
import com.example.Server.modelos.ParaleloMateria;
import com.example.Server.modelos.Estudiante;
import lombok.Data;

@Data
public class DtoMatricula {
    private String estado;
    private ParaleloMateria paraleloMateria;
    private Estudiante estudiante;
}

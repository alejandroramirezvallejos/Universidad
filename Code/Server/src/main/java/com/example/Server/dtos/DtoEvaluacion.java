package com.example.Server.dtos;
import com.example.Server.modelos.Docente;
import com.example.Server.modelos.ParaleloMateria;
import lombok.Data;

@Data
public class DtoEvaluacion {
    private Docente docente;
    private ParaleloMateria paraleloMateria;
    private String nombre;
    private Double porcentaje;
}


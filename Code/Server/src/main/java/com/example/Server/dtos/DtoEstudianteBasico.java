package com.example.Server.dtos;
import lombok.Data;

@Data
public class DtoEstudianteBasico {
    private String codigo;
    private String nombreCompleto;
    private int semestre;
    private String email;
}


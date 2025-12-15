package com.example.Server.dtos;
import lombok.Data;

@Data
public class DtoCalificacionReciente {
    private String materia;
    private String evaluacion;
    private double nota;
    private String fecha;
}


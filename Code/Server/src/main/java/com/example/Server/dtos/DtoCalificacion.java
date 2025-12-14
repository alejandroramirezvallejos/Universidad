package com.example.Server.dtos;
import com.example.Server.modelos.Evaluacion;
import com.example.Server.modelos.Estudiante;
import lombok.Data;

@Data
public class DtoCalificacion {
    private Evaluacion evaluacion;
    private Estudiante estudiante;
    private Double valor;
    private String observaciones;
}


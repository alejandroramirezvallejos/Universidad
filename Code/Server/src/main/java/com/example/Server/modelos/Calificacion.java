package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calificacion {
    private Double valor;
    private String observaciones;
    private Estudiante estudiante;
    private Evaluacion evaluacion;
}

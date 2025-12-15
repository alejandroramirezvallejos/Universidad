package com.example.Server.dtos;
import lombok.Data;

@Data
public class DtoEstadisticaMateria {
    private String materia;
    private double promedioGeneral;
    private int aprobados;
    private int reprobados;
}

package com.example.Server.dtos;
import lombok.Data;
import java.util.List;

@Data
public class DtoDashboardEstudiante {
    private String codigoEstudiante;
    private String nombreCompleto;
    private String carrera;
    private int semestre;
    private int materiasInscritas;
    private int materiasAprobadas;
    private int creditosInscritos;
    private int creditosAprobados;
    private double promedioGeneral;
    private List<DtoMateriaResumen> materiasActuales;
    private List<DtoCalificacionReciente> calificacionesRecientes;
}

package com.example.Server.dtos;
import lombok.Data;
import java.util.List;

@Data
public class DtoReporteRendimiento {
    private String codigoMateria;
    private String nombreMateria;
    private String codigoParalelo;
    private String nombreDocente;
    private int totalEstudiantes;
    private double promedioGeneral;
    private int aprobados;
    private int reprobados;
    private int sinCalificar;
    private List<DtoRendimientoEstudiante> estudiantes;
}

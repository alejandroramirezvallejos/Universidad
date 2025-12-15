package com.example.Server.dtos;
import lombok.Data;
import java.util.List;

@Data
public class DtoReporteEstudiantesPorCarrera {
    private String codigoCarrera;
    private String nombreCarrera;
    private int totalEstudiantes;
    private int estudiantesPorSemestre1;
    private int estudiantesPorSemestre2;
    private int estudiantesPorSemestre3;
    private int estudiantesPorSemestre4;
    private int estudiantesPorSemestre5;
    private int estudiantesPorSemestre6;
    private int estudiantesPorSemestre7;
    private int estudiantesPorSemestre8;
    private List<DtoEstudianteBasico> estudiantes;
}

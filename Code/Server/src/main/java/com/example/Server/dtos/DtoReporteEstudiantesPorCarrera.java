package com.example.Server.dtos;

import java.util.List;

/**
 * DTO para reporte de estudiantes por carrera
 */
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

    // Constructors
    public DtoReporteEstudiantesPorCarrera() {
    }

    // Getters y Setters
    public String getCodigoCarrera() {
        return codigoCarrera;
    }

    public void setCodigoCarrera(String codigoCarrera) {
        this.codigoCarrera = codigoCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public int getTotalEstudiantes() {
        return totalEstudiantes;
    }

    public void setTotalEstudiantes(int totalEstudiantes) {
        this.totalEstudiantes = totalEstudiantes;
    }

    public int getEstudiantesPorSemestre1() {
        return estudiantesPorSemestre1;
    }

    public void setEstudiantesPorSemestre1(int estudiantesPorSemestre1) {
        this.estudiantesPorSemestre1 = estudiantesPorSemestre1;
    }

    public int getEstudiantesPorSemestre2() {
        return estudiantesPorSemestre2;
    }

    public void setEstudiantesPorSemestre2(int estudiantesPorSemestre2) {
        this.estudiantesPorSemestre2 = estudiantesPorSemestre2;
    }

    public int getEstudiantesPorSemestre3() {
        return estudiantesPorSemestre3;
    }

    public void setEstudiantesPorSemestre3(int estudiantesPorSemestre3) {
        this.estudiantesPorSemestre3 = estudiantesPorSemestre3;
    }

    public int getEstudiantesPorSemestre4() {
        return estudiantesPorSemestre4;
    }

    public void setEstudiantesPorSemestre4(int estudiantesPorSemestre4) {
        this.estudiantesPorSemestre4 = estudiantesPorSemestre4;
    }

    public int getEstudiantesPorSemestre5() {
        return estudiantesPorSemestre5;
    }

    public void setEstudiantesPorSemestre5(int estudiantesPorSemestre5) {
        this.estudiantesPorSemestre5 = estudiantesPorSemestre5;
    }

    public int getEstudiantesPorSemestre6() {
        return estudiantesPorSemestre6;
    }

    public void setEstudiantesPorSemestre6(int estudiantesPorSemestre6) {
        this.estudiantesPorSemestre6 = estudiantesPorSemestre6;
    }

    public int getEstudiantesPorSemestre7() {
        return estudiantesPorSemestre7;
    }

    public void setEstudiantesPorSemestre7(int estudiantesPorSemestre7) {
        this.estudiantesPorSemestre7 = estudiantesPorSemestre7;
    }

    public int getEstudiantesPorSemestre8() {
        return estudiantesPorSemestre8;
    }

    public void setEstudiantesPorSemestre8(int estudiantesPorSemestre8) {
        this.estudiantesPorSemestre8 = estudiantesPorSemestre8;
    }

    public List<DtoEstudianteBasico> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<DtoEstudianteBasico> estudiantes) {
        this.estudiantes = estudiantes;
    }
}

// DTO auxiliar
class DtoEstudianteBasico {
    private String codigo;
    private String nombreCompleto;
    private int semestre;
    private String email;

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public int getSemestre() { return semestre; }
    public void setSemestre(int semestre) { this.semestre = semestre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

package com.example.Server.dtos;

import java.util.List;

/**
 * DTO para reporte de rendimiento académico
 */
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

    // Constructors
    public DtoReporteRendimiento() {
    }

    // Getters y Setters
    public String getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(String codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getCodigoParalelo() {
        return codigoParalelo;
    }

    public void setCodigoParalelo(String codigoParalelo) {
        this.codigoParalelo = codigoParalelo;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public int getTotalEstudiantes() {
        return totalEstudiantes;
    }

    public void setTotalEstudiantes(int totalEstudiantes) {
        this.totalEstudiantes = totalEstudiantes;
    }

    public double getPromedioGeneral() {
        return promedioGeneral;
    }

    public void setPromedioGeneral(double promedioGeneral) {
        this.promedioGeneral = promedioGeneral;
    }

    public int getAprobados() {
        return aprobados;
    }

    public void setAprobados(int aprobados) {
        this.aprobados = aprobados;
    }

    public int getReprobados() {
        return reprobados;
    }

    public void setReprobados(int reprobados) {
        this.reprobados = reprobados;
    }

    public int getSinCalificar() {
        return sinCalificar;
    }

    public void setSinCalificar(int sinCalificar) {
        this.sinCalificar = sinCalificar;
    }

    public List<DtoRendimientoEstudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<DtoRendimientoEstudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
}

// DTO auxiliar
class DtoRendimientoEstudiante {
    private String codigo;
    private String nombreCompleto;
    private double notaFinal;
    private String estado; // APROBADO, REPROBADO, SIN_CALIFICAR

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public double getNotaFinal() { return notaFinal; }
    public void setNotaFinal(double notaFinal) { this.notaFinal = notaFinal; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}

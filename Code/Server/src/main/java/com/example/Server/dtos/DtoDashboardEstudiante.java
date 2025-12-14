package com.example.Server.dtos;

import java.util.List;

/**
 * DTO para estadísticas del dashboard de estudiante
 */
public class DtoDashboardEstudiante {
    private String codigoEstudiante;
    private String nombreCompleto;
    private String carrera;
    private int semestre;
    
    // Estadísticas académicas
    private int materiasInscritas;
    private int materiasAprobadas;
    private int creditosInscritos;
    private int creditosAprobados;
    private double promedioGeneral;
    
    // Listas detalladas
    private List<DtoMateriaResumen> materiasActuales;
    private List<DtoCalificacionReciente> calificacionesRecientes;

    // Constructors
    public DtoDashboardEstudiante() {
    }

    // Getters y Setters
    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getMateriasInscritas() {
        return materiasInscritas;
    }

    public void setMateriasInscritas(int materiasInscritas) {
        this.materiasInscritas = materiasInscritas;
    }

    public int getMateriasAprobadas() {
        return materiasAprobadas;
    }

    public void setMateriasAprobadas(int materiasAprobadas) {
        this.materiasAprobadas = materiasAprobadas;
    }

    public int getCreditosInscritos() {
        return creditosInscritos;
    }

    public void setCreditosInscritos(int creditosInscritos) {
        this.creditosInscritos = creditosInscritos;
    }

    public int getCreditosAprobados() {
        return creditosAprobados;
    }

    public void setCreditosAprobados(int creditosAprobados) {
        this.creditosAprobados = creditosAprobados;
    }

    public double getPromedioGeneral() {
        return promedioGeneral;
    }

    public void setPromedioGeneral(double promedioGeneral) {
        this.promedioGeneral = promedioGeneral;
    }

    public List<DtoMateriaResumen> getMateriasActuales() {
        return materiasActuales;
    }

    public void setMateriasActuales(List<DtoMateriaResumen> materiasActuales) {
        this.materiasActuales = materiasActuales;
    }

    public List<DtoCalificacionReciente> getCalificacionesRecientes() {
        return calificacionesRecientes;
    }

    public void setCalificacionesRecientes(List<DtoCalificacionReciente> calificacionesRecientes) {
        this.calificacionesRecientes = calificacionesRecientes;
    }
}

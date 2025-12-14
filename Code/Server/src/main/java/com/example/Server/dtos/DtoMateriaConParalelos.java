package com.example.Server.dtos;

import java.util.List;

/**
 * DTO para materia con sus paralelos disponibles
 */
public class DtoMateriaConParalelos {
    private String codigo;
    private String nombre;
    private int creditos;
    private int semestre;
    private List<DtoParaleloDetalle> paralelos;

    // Constructors
    public DtoMateriaConParalelos() {
    }

    public DtoMateriaConParalelos(String codigo, String nombre, int creditos, int semestre, List<DtoParaleloDetalle> paralelos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.semestre = semestre;
        this.paralelos = paralelos;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public List<DtoParaleloDetalle> getParalelos() {
        return paralelos;
    }

    public void setParalelos(List<DtoParaleloDetalle> paralelos) {
        this.paralelos = paralelos;
    }
}

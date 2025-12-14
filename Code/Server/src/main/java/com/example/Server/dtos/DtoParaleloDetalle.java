package com.example.Server.dtos;

import java.util.List;

/**
 * DTO para paralelo con detalles completos (horarios, cupos, docente)
 */
public class DtoParaleloDetalle {
    private Long id;
    private String numeroParalelo;
    private int cupoTotal;
    private int cupoDisponible;
    private String nombreDocente;
    private String codigoDocente;
    private List<DtoHorario> horarios;

    // Constructors
    public DtoParaleloDetalle() {
    }

    public DtoParaleloDetalle(Long id, String numeroParalelo, int cupoTotal, int cupoDisponible, 
                              String nombreDocente, String codigoDocente, List<DtoHorario> horarios) {
        this.id = id;
        this.numeroParalelo = numeroParalelo;
        this.cupoTotal = cupoTotal;
        this.cupoDisponible = cupoDisponible;
        this.nombreDocente = nombreDocente;
        this.codigoDocente = codigoDocente;
        this.horarios = horarios;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroParalelo() {
        return numeroParalelo;
    }

    public void setNumeroParalelo(String numeroParalelo) {
        this.numeroParalelo = numeroParalelo;
    }

    public int getCupoTotal() {
        return cupoTotal;
    }

    public void setCupoTotal(int cupoTotal) {
        this.cupoTotal = cupoTotal;
    }

    public int getCupoDisponible() {
        return cupoDisponible;
    }

    public void setCupoDisponible(int cupoDisponible) {
        this.cupoDisponible = cupoDisponible;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public String getCodigoDocente() {
        return codigoDocente;
    }

    public void setCodigoDocente(String codigoDocente) {
        this.codigoDocente = codigoDocente;
    }

    public List<DtoHorario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<DtoHorario> horarios) {
        this.horarios = horarios;
    }
}

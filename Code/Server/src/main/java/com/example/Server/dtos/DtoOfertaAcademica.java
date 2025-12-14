package com.example.Server.dtos;

import java.util.List;

/**
 * DTO para la oferta académica completa
 * Combina materias con sus paralelos y horarios
 */
public class DtoOfertaAcademica {
    private String codigoGestion;
    private String nombreGestion;
    private List<DtoMateriaConParalelos> materias;

    // Constructors
    public DtoOfertaAcademica() {
    }

    public DtoOfertaAcademica(String codigoGestion, String nombreGestion, List<DtoMateriaConParalelos> materias) {
        this.codigoGestion = codigoGestion;
        this.nombreGestion = nombreGestion;
        this.materias = materias;
    }

    // Getters y Setters
    public String getCodigoGestion() {
        return codigoGestion;
    }

    public void setCodigoGestion(String codigoGestion) {
        this.codigoGestion = codigoGestion;
    }

    public String getNombreGestion() {
        return nombreGestion;
    }

    public void setNombreGestion(String nombreGestion) {
        this.nombreGestion = nombreGestion;
    }

    public List<DtoMateriaConParalelos> getMaterias() {
        return materias;
    }

    public void setMaterias(List<DtoMateriaConParalelos> materias) {
        this.materias = materias;
    }
}

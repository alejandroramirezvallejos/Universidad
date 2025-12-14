package com.example.Server.dtos;

import java.util.List;

/**
 * DTO para reporte de inscripciones por gestión
 */
public class DtoReporteInscripciones {
    private String codigoGestion;
    private String nombreGestion;
    private int totalInscripciones;
    private int inscripcionesPendientes;
    private int inscripcionesAceptadas;
    private int inscripcionesRechazadas;
    private List<DtoInscripcionDetalle> inscripciones;

    // Constructors
    public DtoReporteInscripciones() {
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

    public int getTotalInscripciones() {
        return totalInscripciones;
    }

    public void setTotalInscripciones(int totalInscripciones) {
        this.totalInscripciones = totalInscripciones;
    }

    public int getInscripcionesPendientes() {
        return inscripcionesPendientes;
    }

    public void setInscripcionesPendientes(int inscripcionesPendientes) {
        this.inscripcionesPendientes = inscripcionesPendientes;
    }

    public int getInscripcionesAceptadas() {
        return inscripcionesAceptadas;
    }

    public void setInscripcionesAceptadas(int inscripcionesAceptadas) {
        this.inscripcionesAceptadas = inscripcionesAceptadas;
    }

    public int getInscripcionesRechazadas() {
        return inscripcionesRechazadas;
    }

    public void setInscripcionesRechazadas(int inscripcionesRechazadas) {
        this.inscripcionesRechazadas = inscripcionesRechazadas;
    }

    public List<DtoInscripcionDetalle> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<DtoInscripcionDetalle> inscripciones) {
        this.inscripciones = inscripciones;
    }
}

// DTO auxiliar
class DtoInscripcionDetalle {
    private String codigoEstudiante;
    private String nombreEstudiante;
    private String codigoMateria;
    private String nombreMateria;
    private String paralelo;
    private String estado;

    public String getCodigoEstudiante() { return codigoEstudiante; }
    public void setCodigoEstudiante(String codigoEstudiante) { this.codigoEstudiante = codigoEstudiante; }
    public String getNombreEstudiante() { return nombreEstudiante; }
    public void setNombreEstudiante(String nombreEstudiante) { this.nombreEstudiante = nombreEstudiante; }
    public String getCodigoMateria() { return codigoMateria; }
    public void setCodigoMateria(String codigoMateria) { this.codigoMateria = codigoMateria; }
    public String getNombreMateria() { return nombreMateria; }
    public void setNombreMateria(String nombreMateria) { this.nombreMateria = nombreMateria; }
    public String getParalelo() { return paralelo; }
    public void setParalelo(String paralelo) { this.paralelo = paralelo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}

package com.example.Server.dtos;
import lombok.Data;
import java.util.List;

@Data
public class DtoReporteInscripciones {
    private String codigoGestion;
    private String nombreGestion;
    private int totalInscripciones;
    private int inscripcionesPendientes;
    private int inscripcionesAceptadas;
    private int inscripcionesRechazadas;
    private List<DtoInscripcionDetalle> inscripciones;
}

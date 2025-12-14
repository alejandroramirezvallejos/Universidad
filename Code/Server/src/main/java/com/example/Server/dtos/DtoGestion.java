package com.example.Server.dtos;
import lombok.Data;

/**
 * DTO para Gestion (periodo académico)
 */
@Data
public class DtoGestion {
    private String codigo;                  // "II-2025"
    private String nombre;                  // "Segundo Semestre 2025"
    private Integer anio;                   // 2025
    private Integer periodo;                // 1 o 2
    private String fechaInicio;             // "2025-08-01" (ISO 8601)
    private String fechaFin;                // "2025-12-15"
    private String fechaInicioMatricula;    // "2025-07-15"
    private String fechaFinMatricula;       // "2025-07-30"
    private String estado;                  // EN_CURSO, CERRADA, PLANIFICADA
}

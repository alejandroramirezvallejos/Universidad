package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * Modelo Gestion - Representa un periodo académico
 * Ejemplo: "II-2025" (Segundo semestre del 2025)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gestion {
    private String codigo;                  // "II-2025", "I-2025"
    private String nombre;                  // "Segundo Semestre 2025"
    private Integer anio;                   // 2025
    private Integer periodo;                // 1 o 2
    private Date fechaInicio;               // Inicio de clases
    private Date fechaFin;                  // Fin de clases
    private Date fechaInicioMatricula;      // Inicio periodo de matrícula
    private Date fechaFinMatricula;         // Fin periodo de matrícula
    private String estado;                  // EN_CURSO, CERRADA, PLANIFICADA
    
    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }
}

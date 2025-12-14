package com.example.Server.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para transferir datos de Horario
 * Usa String para las horas en formato "HH:mm"
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoHorario {
    private String diaSemana;  // LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO
    private String horaInicio; // "08:00"
    private String horaFin;    // "10:00"
}

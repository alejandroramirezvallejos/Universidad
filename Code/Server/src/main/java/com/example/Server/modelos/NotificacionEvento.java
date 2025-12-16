package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificacionEvento {
    private Estudiante estudiante;
    private Materia materia;
    private Double notaFinal;
}

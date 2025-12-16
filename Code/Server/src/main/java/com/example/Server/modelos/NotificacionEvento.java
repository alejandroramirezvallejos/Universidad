package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionEvento {
    private Estudiante estudiante;
    private Materia materia;
    private Double notaFinal;
}

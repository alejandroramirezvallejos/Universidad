package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Horario {
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}


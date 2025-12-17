package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.IHorario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Horario implements IHorario {
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}

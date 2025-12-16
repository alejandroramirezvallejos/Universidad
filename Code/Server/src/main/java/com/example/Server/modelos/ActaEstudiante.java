package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActaEstudiante {
    private Estudiante estudiante;
    private ParaleloMateria paraleloMateria;
    @Builder.Default
    private List<Calificacion> calificaciones = new ArrayList<>();
    private Double calificacionFinal;
    private boolean aprobado;
}

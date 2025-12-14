package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActaEstudiante {
    private Estudiante estudiante;
    private ParaleloMateria paraleloMateria;
    private List<Calificacion> calificaciones = new ArrayList<>();
    private Double calificacionFinal;
    private boolean aprobado;
}


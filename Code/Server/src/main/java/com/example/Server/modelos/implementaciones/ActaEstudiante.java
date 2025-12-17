package com.example.Server.modelos.implementaciones;

import com.example.Server.modelos.abstracciones.IActaEstudiante;
import com.example.Server.modelos.abstracciones.ICalificacion;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class ActaEstudiante implements IActaEstudiante {
    @JsonIgnoreProperties({"materiasInscritas", "materiasAprobadas", "carrera"})
    private IEstudiante estudiante;
    @JsonIgnoreProperties({"estudiantes", "horarios", "docente"})
    private IParaleloMateria paraleloMateria;
    @Builder.Default
    @JsonIgnoreProperties({"estudiante", "evaluacion"})
    private List<ICalificacion> calificaciones = new ArrayList<>();
    private Double calificacionFinal;
    private boolean aprobado;
}

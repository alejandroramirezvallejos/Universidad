package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.ICalificacion;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IEvaluacion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Calificacion implements ICalificacion {
    private Double valor;
    private String observaciones;
    @JsonIgnoreProperties({"materiasInscritas", "materiasAprobadas", "carrera"})
    private IEstudiante estudiante;
    @JsonIgnoreProperties({"calificaciones", "paraleloMateria"})
    private IEvaluacion evaluacion;
}

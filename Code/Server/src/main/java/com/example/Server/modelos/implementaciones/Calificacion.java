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
    private Estudiante estudiante;  // Cambiado de IEstudiante a Estudiante
    @JsonIgnoreProperties({"calificaciones", "paraleloMateria"})
    private Evaluacion evaluacion;  // Cambiado de IEvaluacion a Evaluacion

    // Getters para las interfaces (requeridos por ICalificacion)
    @Override
    public IEstudiante getEstudiante() {
        return estudiante;
    }

    @Override
    public IEvaluacion getEvaluacion() {
        return evaluacion;
    }

    // Setters para las interfaces
    @Override
    public void setEstudiante(IEstudiante estudiante) {
        this.estudiante = (Estudiante) estudiante;
    }

    @Override
    public void setEvaluacion(IEvaluacion evaluacion) {
        this.evaluacion = (Evaluacion) evaluacion;
    }
}

package com.example.Server.modelos.implementaciones;

import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IMatricula;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Matricula implements IMatricula {
    private String estado;
    @JsonIgnoreProperties({ "estudiantes" })
    private ParaleloMateria paraleloMateria; // Cambiado de IParaleloMateria a ParaleloMateria
    @JsonIgnoreProperties({ "materiasInscritas", "materiasAprobadas", "carrera" })
    private Estudiante estudiante; // Cambiado de IEstudiante a Estudiante

    // Métodos para mantener compatibilidad con la interfaz IMatricula
    @Override
    public IParaleloMateria getParaleloMateria() {
        return paraleloMateria;
    }

    @Override
    public void setParaleloMateria(IParaleloMateria paraleloMateria) {
        this.paraleloMateria = (ParaleloMateria) paraleloMateria;
    }

    @Override
    public IEstudiante getEstudiante() {
        return estudiante;
    }

    @Override
    public void setEstudiante(IEstudiante estudiante) {
        this.estudiante = (Estudiante) estudiante;
    }
}

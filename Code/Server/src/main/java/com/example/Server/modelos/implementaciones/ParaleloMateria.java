package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParaleloMateria implements IParaleloMateria {
    private String codigo;
    @JsonIgnoreProperties({"paraleloMaterias", "materiasCorrelativas", "carrera"})
    @ToString.Exclude
    private Materia materia;  // Cambiado de IMateria a Materia
    @JsonIgnoreProperties({"paraleloMaterias"})
    @ToString.Exclude
    private Docente docente;  // Cambiado de IDocente a Docente
    @JsonIgnoreProperties({"paraleloMaterias"})
    private Aula aula;  // Cambiado de IAula a Aula
    @JsonIgnoreProperties({"materias"})
    private Gestion gestion;  // Cambiado de IGestion a Gestion
    private Integer cupoMaximo;
    @Builder.Default
    @JsonIgnoreProperties({"materiasInscritas", "materiasAprobadas", "carrera"})
    @ToString.Exclude
    private List<IEstudiante> estudiantes = new ArrayList<>();
    @Builder.Default
    private List<Horario> horarios = new ArrayList<>();  // Cambiado de IHorario a Horario
}

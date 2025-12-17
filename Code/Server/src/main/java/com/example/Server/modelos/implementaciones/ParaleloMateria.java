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
    private Materia materia; 
    @JsonIgnoreProperties({"paraleloMaterias"})
    @ToString.Exclude
    private Docente docente;  
    @JsonIgnoreProperties({"paraleloMaterias"})
    private Aula aula;
    @JsonIgnoreProperties({"materias"})
    private Gestion gestion; 
    private Integer cupoMaximo;
    @Builder.Default
    @JsonIgnoreProperties({"materiasInscritas", "materiasAprobadas", "carrera"})
    @ToString.Exclude
    private List<IEstudiante> estudiantes = new ArrayList<>();
    @Builder.Default
    private List<Horario> horarios = new ArrayList<>(); 
}

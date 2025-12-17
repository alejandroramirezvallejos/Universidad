package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.*;
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
public class ParaleloMateria implements IParaleloMateria {
    private String codigo;
    @JsonIgnoreProperties({"paraleloMaterias", "materiasCorrelativas"})
    private IMateria materia;
    @JsonIgnoreProperties({"paraleloMaterias"})
    private IDocente docente;
    private IAula aula;
    private IGestion gestion;
    private Integer cupoMaximo;
    @Builder.Default
    @JsonIgnoreProperties({"materiasInscritas", "materiasAprobadas", "carrera"})
    private List<IEstudiante> estudiantes = new ArrayList<>();
    @Builder.Default
    private List<IHorario> horarios = new ArrayList<>();
}

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
public class ParaleloMateria {
    private String codigo;
    private Materia materia;
    private Docente docente;
    private Aula aula;
    private Gestion gestion;
    private Integer cupoMaximo;
    @Builder.Default
    private List<Estudiante> estudiantes = new ArrayList<>();
    @Builder.Default
    private List<Horario> horarios = new ArrayList<>();
}

package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.*;
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
    private IMateria materia;
    private IDocente docente;
    private IAula aula;
    private IGestion gestion;
    private Integer cupoMaximo;
    @Builder.Default
    private List<IEstudiante> estudiantes = new ArrayList<>();
    @Builder.Default
    private List<IHorario> horarios = new ArrayList<>();
}

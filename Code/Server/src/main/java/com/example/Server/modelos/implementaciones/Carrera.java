package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.ICarrera;
import com.example.Server.modelos.abstracciones.IDirectorCarrera;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IMateria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Carrera implements ICarrera {
    private String nombre;
    private String codigo;
    @Builder.Default
    @JsonIgnoreProperties({"carrera", "materiasInscritas", "materiasAprobadas"})
    private List<IEstudiante> estudiantes = new ArrayList<>();
    @JsonIgnoreProperties({"carrera"})
    private IDirectorCarrera director;
    @Builder.Default
    @JsonIgnoreProperties({"carrera", "paraleloMaterias", "materiasCorrelativas"})
    private List<IMateria> materias = new ArrayList<>();
}

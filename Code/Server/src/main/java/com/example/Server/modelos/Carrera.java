package com.example.Server.modelos;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Carrera {
    private String nombre;
    private String codigo;
    private List<Estudiante> estudiantes;
    private DirectorCarrera director;
    private List<Materia> materias;
}



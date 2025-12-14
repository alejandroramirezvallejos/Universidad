package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParaleloMateria {
    private String codigo;
    private Materia materia;
    private Docente docente;
    private Aula aula;
    private Gestion gestion; // Agregado para vincular paralelo a una gestión
    private Integer cupoMaximo;
    private List<Estudiante> estudiantes = new ArrayList<>();
    private List<Horario> horarios = new ArrayList<>();
}


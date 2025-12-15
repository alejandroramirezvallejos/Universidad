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
    private Gestion gestion;
    private Integer cupoMaximo;
    private List<Estudiante> estudiantes = new ArrayList<>();
    private List<Horario> horarios = new ArrayList<>();

    public boolean estaInscrito(Estudiante estudiante) {
        if (estudiantes == null) return false;

        for (Estudiante e : estudiantes)
            if (e.getCodigo().equals(estudiante.getCodigo()))
                return true;

        return false;
    }

    public Horario getHorarioChoque(List<Horario> otrosHorarios) {
        if (horarios == null)
            return null;

        for (Horario otro : otrosHorarios) {
            Horario choque = buscarChoque(otro);

            if (choque != null)
                return choque;
        }
        return null;
    }

    private Horario buscarChoque(Horario otro) {
        for (Horario h : horarios)
            if (h.seSolapa(otro)) return h;
        return null;
    }

    public boolean tieneCupo() {
        if (estudiantes == null) return true;
        return estudiantes.size() < cupoMaximo;
    }
}

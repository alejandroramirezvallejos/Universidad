package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante implements Usuario {
    private String codigo;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasenna;
    private int semestre;
    private Carrera carrera;
    private List<Materia> materiasInscritas = new ArrayList<>();
    private List<Materia> materiasAprobadas = new ArrayList<>();

    public int calcularCreditosInscritos() {
        if (materiasInscritas == null) return 0;
        int total = 0;
        for (Materia materia : materiasInscritas) {
            total += materia.getCreditos();
        }
        return total;
    }

    public boolean haAprobado(Materia materia) {
        if (materiasAprobadas == null) return false;
        for (Materia aprobada : materiasAprobadas) {
            if (aprobada.getCodigo().equals(materia.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public boolean estaInscritoEnMateria(Materia materia) {
        if (materiasInscritas == null) return false;
        for (Materia inscrita : materiasInscritas) {
            if (inscrita.getCodigo().equals(materia.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public Horario obtenerChoqueHorario(List<Horario> nuevosHorarios) {
        if (materiasInscritas == null) return null;
        for (Materia materia : materiasInscritas) {
            ParaleloMateria paralelo = materia.getParaleloInscrito(this);
            if (paralelo != null) {
                Horario choque = paralelo.getHorarioChoque(nuevosHorarios);
                if (choque != null) return choque;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}

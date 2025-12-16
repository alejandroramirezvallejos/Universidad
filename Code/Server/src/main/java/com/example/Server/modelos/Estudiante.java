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
public class Estudiante extends Usuario {
    private int semestre;
    private Carrera carrera;
    private List<Materia> materiasInscritas = new ArrayList<>();
    private List<Materia> materiasAprobadas = new ArrayList<>();

    @Override
    public String toString() {
        return getNombre() + " " + getApellido();
    }
}

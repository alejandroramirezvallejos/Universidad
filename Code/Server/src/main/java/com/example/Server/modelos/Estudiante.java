package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Estudiante extends Usuario {
    private int semestre;
    private Carrera carrera;
    @Builder.Default
    private List<Materia> materiasInscritas = new ArrayList<>();
    @Builder.Default
    private List<Materia> materiasAprobadas = new ArrayList<>();

    @Override
    public String toString() {
        return getNombre() + " " + getApellido();
    }
}


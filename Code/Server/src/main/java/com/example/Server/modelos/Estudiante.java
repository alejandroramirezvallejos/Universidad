package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {
    private String codigo;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasenna;
    private int semestre;
    private Carrera carrera;
    private List<Materia> materiasInscritas = new ArrayList<>();
    private List<Materia> materiasAprobadas = new ArrayList<>();

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}


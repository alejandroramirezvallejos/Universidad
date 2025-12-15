package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Docente implements Usuario {
    private String codigo;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasenna;
    private String departamento;
    private String especialidad;
    private boolean activo = true;
    private List<ParaleloMateria> paraleloMaterias = new ArrayList<>();

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}


package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectorCarrera {
    private String codigo;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasenna;
    private String departamento;
    private Carrera carrera;

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}


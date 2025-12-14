package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Docente {
    private String codigo;
    private String nombre;
    private String apellido;
    private String email;
    private String password; // Agregado para autenticación simple
    private String departamento; // Agregado para registro
    private String especialidad;
    private boolean activo = true;
    private List<ParaleloMateria> paraleloMaterias = new ArrayList<>();

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}


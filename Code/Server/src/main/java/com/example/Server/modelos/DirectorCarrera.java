package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectorCarrera extends Usuario {
    private String departamento;
    private Carrera carrera;

    @Override
    public String toString() {
        return getNombre() + " " + getApellido();
    }
}

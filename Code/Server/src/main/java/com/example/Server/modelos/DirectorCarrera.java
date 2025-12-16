package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DirectorCarrera extends Usuario {
    private String departamento;
    private Carrera carrera;

    @Override
    public String toString() {
        return getNombre() + " " + getApellido();
    }
}




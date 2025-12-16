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
public class Docente extends Usuario {
    private String departamento;
    private String especialidad;
    @Builder.Default
    private boolean activo = true;
    @Builder.Default
    private List<ParaleloMateria> paraleloMaterias = new ArrayList<>();

    @Override
    public String toString() {
        return getNombre() + " " + getApellido();
    }
}

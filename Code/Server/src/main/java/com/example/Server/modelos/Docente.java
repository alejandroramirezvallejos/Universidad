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
public class Docente extends Usuario {
    private String departamento;
    private String especialidad;
    private boolean activo = true;
    private List<ParaleloMateria> paraleloMaterias = new ArrayList<>();

    @Override
    public String toString() {
        return getNombre() + " " + getApellido();
    }
}

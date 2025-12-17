package com.example.Server.modelos.implementaciones;

import com.example.Server.modelos.abstracciones.IDocente;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import com.example.Server.modelos.abstracciones.AUsuario;
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
public class Docente extends AUsuario implements IDocente {
    private String departamento;
    private String especialidad;
    @Builder.Default
    private boolean activo = true;
    @Builder.Default
    private List<IParaleloMateria> paraleloMaterias = new ArrayList<>();

    @Override
    public String toString() {
        return getNombre() + " " + getApellido();
    }
}

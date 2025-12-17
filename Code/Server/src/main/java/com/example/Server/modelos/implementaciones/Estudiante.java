package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.ICarrera;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IMateria;
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
public class Estudiante extends AUsuario implements IEstudiante {
    private int semestre;
    private ICarrera carrera;
    @Builder.Default
    private List<IMateria> materiasInscritas = new ArrayList<>();
    @Builder.Default
    private List<IMateria> materiasAprobadas = new ArrayList<>();

    @Override
    public String toString() {
        return getNombre() + " " + getApellido();
    }
}

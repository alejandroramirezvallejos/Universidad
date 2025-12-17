package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.ICarrera;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IMateria;
import com.example.Server.modelos.abstracciones.AUsuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties({"estudiantes", "materias", "director"})
    private Carrera carrera;
    @Builder.Default
    @JsonIgnoreProperties({"paraleloMaterias", "materiasCorrelativas", "carrera"})
    private List<IMateria> materiasInscritas = new ArrayList<>();
    @Builder.Default
    @JsonIgnoreProperties({"paraleloMaterias", "materiasCorrelativas", "carrera"})
    private List<IMateria> materiasAprobadas = new ArrayList<>();

    // Métodos para cumplir con la interfaz IEstudiante que usa ICarrera
    @Override
    public ICarrera getCarrera() {
        return this.carrera;
    }

    @Override
    public void setCarrera(ICarrera carrera) {
        this.carrera = (Carrera) carrera;
    }

    @Override
    public String toString() {
        return getNombre() + " " + getApellido();
    }
}

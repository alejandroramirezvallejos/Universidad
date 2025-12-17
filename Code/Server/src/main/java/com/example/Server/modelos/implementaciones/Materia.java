package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.ICarrera;
import com.example.Server.modelos.abstracciones.IMateria;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
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
public class Materia implements IMateria {
    private String codigo;
    private String nombre;
    private Integer semestre;
    private Integer creditos;
    @Builder.Default
    private boolean activa = true;
    @Builder.Default
    private List<IMateria> materiasCorrelativas = new ArrayList<>();
    @Builder.Default
    private List<IParaleloMateria> paraleloMaterias = new ArrayList<>();
    private ICarrera carrera;

    public Materia clonar() {
        List<IMateria> correlativas = new ArrayList<>();

        for (IMateria materia : this.materiasCorrelativas)
            correlativas.add(materia.clonar());

        return Materia.builder()
            .codigo(this.codigo)
            .nombre(this.nombre)
            .semestre(this.semestre)
            .creditos(this.creditos)
            .activa(this.activa)
            .materiasCorrelativas(correlativas)
            .paraleloMaterias(new ArrayList<>(this.paraleloMaterias))
            .carrera(this.carrera)
            .build();
    }
}

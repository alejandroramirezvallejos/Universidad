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
public class Materia implements IMateria {
    private String codigo;
    private String nombre;
    private Integer semestre;
    private Integer creditos;
    @Builder.Default
    private boolean activa = true;
    @Builder.Default
    private List<Materia> materiasCorrelativas = new ArrayList<>();
    @Builder.Default
    private List<ParaleloMateria> paraleloMaterias = new ArrayList<>();
    private Carrera carrera;

    public Materia clonar() {
        List<Materia> correlativas = new ArrayList<>();

        for (Materia materia : this.materiasCorrelativas)
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

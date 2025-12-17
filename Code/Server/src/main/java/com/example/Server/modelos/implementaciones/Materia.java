package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.IMateria;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
    private Boolean activa = true;  // Cambiado de boolean a Boolean para permitir null en JSON
    @Builder.Default
    @JsonIgnoreProperties({"paraleloMaterias", "carrera"})
    @ToString.Exclude
    private List<IMateria> materiasCorrelativas = new ArrayList<>();
    @Builder.Default
    @JsonIgnoreProperties({"materia", "estudiantes", "horarios"})
    @ToString.Exclude
    private List<IParaleloMateria> paraleloMaterias = new ArrayList<>();
    
    // Campo de tipo Carrera (clase concreta) - Jackson puede deserializarlo sin problemas
    @JsonIgnoreProperties({"estudiantes", "materias", "director"})
    private Carrera carrera;

    // Sobrescribir métodos para Boolean en lugar de boolean
    public Boolean isActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

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

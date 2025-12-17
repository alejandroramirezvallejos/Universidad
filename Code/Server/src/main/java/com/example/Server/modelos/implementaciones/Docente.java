package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.IDocente;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
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
public class Docente extends AUsuario implements IDocente {
    private String departamento;
    private String especialidad;
    @Builder.Default
    private Boolean activo = true;  // Cambiado de boolean a Boolean para permitir null en JSON
    @Builder.Default
    @JsonIgnoreProperties({"docente", "materia", "estudiantes", "horarios"})
    private List<IParaleloMateria> paraleloMaterias = new ArrayList<>();

    // Sobrescribir métodos para Boolean en lugar de boolean
    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return getNombre() + " " + getApellido();
    }
}

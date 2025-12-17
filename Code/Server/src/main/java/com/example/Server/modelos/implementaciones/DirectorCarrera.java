package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.IDirectorCarrera;
import com.example.Server.modelos.abstracciones.AUsuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class DirectorCarrera extends AUsuario implements IDirectorCarrera {
    private String departamento;
    
    @JsonIgnoreProperties({"director", "estudiantes", "materias"})
    private Carrera carrera;

    @Override
    public String toString() {
        return getNombre() + " " + getApellido();
    }
}

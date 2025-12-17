package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.ICalificacion;
import com.example.Server.modelos.abstracciones.IEvaluacion;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Evaluacion implements IEvaluacion {
    @Builder.Default
    private String codigo = UUID.randomUUID().toString();
    private String nombre;
    private Double porcentaje;
    @JsonIgnoreProperties({"docente", "estudiantes", "horarios", "gestion", "aula"})  // Incluimos materia
    private ParaleloMateria paraleloMateria;
    @Builder.Default
    @JsonIgnoreProperties({"evaluacion"})
    private List<ICalificacion> calificaciones = new ArrayList<>();

    // Getter para la interfaz (requerido por IEvaluacion)
    @Override
    public IParaleloMateria getParaleloMateria() {
        return paraleloMateria;
    }

    // Setter para la interfaz
    @Override
    public void setParaleloMateria(IParaleloMateria paraleloMateria) {
        this.paraleloMateria = (ParaleloMateria) paraleloMateria;
    }
}

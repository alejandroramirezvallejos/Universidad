package com.example.Server.modelos.implementaciones;

import com.example.Server.modelos.abstracciones.ICalificacion;
import com.example.Server.modelos.abstracciones.IEvaluacion;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
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
    private IParaleloMateria paraleloMateria;
    @Builder.Default
    private List<ICalificacion> calificaciones = new ArrayList<>();
}

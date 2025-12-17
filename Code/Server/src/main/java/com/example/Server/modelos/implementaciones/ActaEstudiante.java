package com.example.Server.modelos.implementaciones;

import com.example.Server.modelos.abstracciones.IActaEstudiante;
import com.example.Server.modelos.abstracciones.ICalificacion;
import com.example.Server.modelos.abstracciones.IEstudiante;
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
public class ActaEstudiante implements IActaEstudiante {
    private IEstudiante estudiante;
    private IParaleloMateria paraleloMateria;
    @Builder.Default
    private List<ICalificacion> calificaciones = new ArrayList<>();
    private Double calificacionFinal;
    private boolean aprobado;
}

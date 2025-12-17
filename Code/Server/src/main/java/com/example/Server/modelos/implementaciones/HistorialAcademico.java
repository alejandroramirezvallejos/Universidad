package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.IActaEstudiante;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IHistorialAcademico;
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
public class HistorialAcademico implements IHistorialAcademico {
    private IEstudiante estudiante;
    @Builder.Default
    private List<IActaEstudiante> actas = new ArrayList<>();
    private Double promedioGeneral;
    private Integer creditosAprobados;
    private Integer creditosTotales;
}

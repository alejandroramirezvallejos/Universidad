package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.IActaEstudiante;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IHistorialAcademico;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties({"materiasInscritas", "materiasAprobadas", "carrera"})
    private IEstudiante estudiante;
    @Builder.Default
    @JsonIgnoreProperties({"estudiante"})
    private List<IActaEstudiante> actas = new ArrayList<>();
    private Double promedioGeneral;
    private Integer creditosAprobados;
    private Integer creditosTotales;
}

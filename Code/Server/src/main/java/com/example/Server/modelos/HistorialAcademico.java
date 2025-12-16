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
public class HistorialAcademico {
    private Estudiante estudiante;
    @Builder.Default
    private List<ActaEstudiante> actas = new ArrayList<>();
    private Double promedioGeneral;
    private Integer creditosAprobados;
    private Integer creditosTotales;
}

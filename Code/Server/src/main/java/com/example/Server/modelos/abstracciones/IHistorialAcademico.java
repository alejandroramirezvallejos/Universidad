package com.example.Server.modelos.abstracciones;

import java.util.List;

public interface IHistorialAcademico {
    IEstudiante getEstudiante();
    void setEstudiante(IEstudiante estudiante);
    List<IActaEstudiante> getActas();
    void setActas(List<IActaEstudiante> actas);
    Double getPromedioGeneral();
    void setPromedioGeneral(Double promedioGeneral);
    Integer getCreditosAprobados();
    void setCreditosAprobados(Integer creditosAprobados);
    Integer getCreditosTotales();
    void setCreditosTotales(Integer creditosTotales);
}

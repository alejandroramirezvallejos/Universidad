package com.example.Server.estrategias.historial;
import com.example.Server.modelos.ActaEstudiante;
import java.util.List;

public interface IEstrategiaCalculoHistorial {
    double calcular(List<ActaEstudiante> actas);
}

package com.example.Server.estrategias.actas;
import com.example.Server.modelos.abstracciones.IActaEstudiante;
import java.util.List;

public interface IEstrategiaCalculoPromedioActas {
    double calcular(List<IActaEstudiante> actas);
}


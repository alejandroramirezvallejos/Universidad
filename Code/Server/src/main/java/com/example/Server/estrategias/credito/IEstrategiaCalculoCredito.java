package com.example.Server.estrategias.credito;
import com.example.Server.modelos.ActaEstudiante;
import java.util.List;

public interface IEstrategiaCalculoCredito {
    double calcular(List<ActaEstudiante> actas);
}


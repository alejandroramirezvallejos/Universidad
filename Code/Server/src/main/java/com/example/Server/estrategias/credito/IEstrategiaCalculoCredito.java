package com.example.Server.estrategias.credito;

import com.example.Server.modelos.abstracciones.IActaEstudiante;
import java.util.List;

public interface IEstrategiaCalculoCredito {
    double calcular(List<IActaEstudiante> actas);
}

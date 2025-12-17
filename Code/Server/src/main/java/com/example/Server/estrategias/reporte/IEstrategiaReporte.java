package com.example.Server.estrategias.reporte;
import com.example.Server.modelos.abstracciones.AReporte;
import java.util.Map;

public interface IEstrategiaReporte {
    Map<String, Object> generar(AReporte reporte);
}

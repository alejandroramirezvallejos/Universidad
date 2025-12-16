package com.example.Server.estrategias.reporte;
import com.example.Server.modelos.Reporte;
import java.util.Map;

public interface IEstrategiaReporte {
    Map<String, Object> generar(Reporte reporte);
}

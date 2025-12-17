package com.example.Server.servicios.abstracciones;
import java.util.List;
import java.util.Map;

public interface IServicioReporte {
    Map<String, Object> getReporteEstudiantesPorCarrera(String codigoCarrera, String solicitante);
    Map<String, Object> getReporteInscripcionesPorGestion(String codigoGestion);
    Map<String, Object> getReporteRendimientoPorParalelo(String codigoParalelo, String solicitante);
    List<String> getReportesDisponibles();
}


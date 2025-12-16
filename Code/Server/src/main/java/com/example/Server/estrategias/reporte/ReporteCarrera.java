package com.example.Server.estrategias.reporte;
import com.example.Server.modelos.Reporte;
import com.example.Server.modelos.ReporteDeCarrera;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class ReporteCarrera implements IEstrategiaReporte {
    @Override
    public Map<String, Object> generar(Reporte reporte) {
        if (!(reporte instanceof ReporteDeCarrera reporteDeCarrera))
            return null;

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("fechaGeneracion", reporteDeCarrera.getFechaGeneracion());
        resultado.put("solicitante", reporteDeCarrera.getSolicitante());
        resultado.put("codigoCarrera", reporteDeCarrera.getCarrera().getCodigo());
        resultado.put("nombreCarrera", reporteDeCarrera.getCarrera().getNombre());
        resultado.put("totalEstudiantes", reporteDeCarrera.getEstudiantes().size());
        resultado.put("estudiantes", reporteDeCarrera.getEstudiantes());

        return resultado;
    }
}

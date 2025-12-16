package com.example.Server.estrategias.reporte;
import com.example.Server.modelos.Reporte;
import com.example.Server.modelos.ReporteDeInscripciones;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class ReporteInscripciones implements IEstrategiaReporte {
    @Override
    public Map<String, Object> generar(Reporte reporte) {
        if (!(reporte instanceof ReporteDeInscripciones reporteDeInscripciones))
            return null;

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("fechaGeneracion", reporteDeInscripciones.getFechaGeneracion());
        resultado.put("solicitante", reporteDeInscripciones.getSolicitante());
        resultado.put("codigoGestion", reporteDeInscripciones.getGestion().getCodigo());
        resultado.put("nombreGestion", reporteDeInscripciones.getGestion().getNombre());
        resultado.put("totalInscripciones", reporteDeInscripciones.getMatriculas().size());
        resultado.put("inscripciones", reporteDeInscripciones.getMatriculas());

        return resultado;
    }
}

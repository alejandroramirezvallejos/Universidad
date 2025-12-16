package com.example.Server.estrategias.reporte;

import com.example.Server.modelos.Reporte;
import com.example.Server.validadores.reporte.IValidarReporte;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class ContextoReportes {
    private final List<IEstrategiaReporte> estrategias;
    private final IValidarReporte validador;

    public Map<String, Object> generarReporte(Reporte reporte) {
        validador.validar(reporte);

        for (IEstrategiaReporte estrategia : estrategias) {
            Map<String, Object> reportes = estrategia.generar(reporte);

            if (reportes != null)
                return reportes;
        }

        return null;
    }
}

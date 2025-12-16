package com.example.Server.estrategias.reporte;
import com.example.Server.modelos.Reporte;
import com.example.Server.modelos.ReporteDeRendimiento;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class ReporteRendimiento implements IEstrategiaReporte {
    @Override
    public Map<String, Object> generar(Reporte reporte) {
        if (!(reporte instanceof ReporteDeRendimiento reporteDeRendimiento))
            return null;

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("fechaGeneracion", reporteDeRendimiento.getFechaGeneracion());
        resultado.put("solicitante", reporteDeRendimiento.getSolicitante());
        resultado.put("codigoMateria", reporteDeRendimiento.getParalelo().getMateria().getCodigo());
        resultado.put("nombreMateria", reporteDeRendimiento.getParalelo().getMateria().getNombre());
        resultado.put("codigoParalelo", reporteDeRendimiento.getParalelo().getCodigo());
        resultado.put("totalEstudiantes", reporteDeRendimiento.getParalelo().getEstudiantes().size());
        resultado.put("paralelo", reporteDeRendimiento.getParalelo());

        return resultado;
    }
}

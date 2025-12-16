package com.example.Server.validadores.reporte;
import com.example.Server.modelos.Reporte;
import com.example.Server.modelos.ReporteDeCarrera;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ValidarReporteDeCarrera implements IValidarReporte {
    @Override
    public void validar(Reporte reporte) {
        if (reporte instanceof ReporteDeCarrera reporteDeCarrera)
            if (reporteDeCarrera.getCarrera() == null)
                throw new IllegalArgumentException("La carrera no puede ser nula");
    }
}

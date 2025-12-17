package com.example.Server.validadores.reporte;
import com.example.Server.modelos.abstracciones.AReporte;
import com.example.Server.modelos.implementaciones.ReporteDeCarrera;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ValidarReporteDeCarrera implements IValidarReporte {
    @Override
    public void validar(AReporte reporte) {
        if (reporte instanceof ReporteDeCarrera reporteDeCarrera)
            if (reporteDeCarrera.getCarrera() == null)
                throw new IllegalArgumentException("La carrera no puede ser nula");
    }
}

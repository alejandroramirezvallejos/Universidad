package com.example.Server.validadores.reporte;
import com.example.Server.modelos.Reporte;
import com.example.Server.modelos.ReporteDeInscripciones;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ValidarReporteDeGestion implements IValidarReporte {
    @Override
    public void validar(Reporte reporte) {
        if (reporte instanceof ReporteDeInscripciones reporteDeInscripciones)
            if (reporteDeInscripciones.getGestion() == null)
                throw new IllegalArgumentException("La gestión no puede ser nula");
    }
}

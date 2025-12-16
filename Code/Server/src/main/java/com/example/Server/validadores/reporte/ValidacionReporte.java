package com.example.Server.validadores.reporte;
import com.example.Server.modelos.Reporte;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class ValidacionReporte implements IValidarReporte {
    private final List<IValidarReporte> validadores;

    @Override
    public void validar(Reporte reporte) {
        for (IValidarReporte validador : validadores)
            if (validador != this)
                validador.validar(reporte);
    }
}

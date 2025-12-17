package com.example.Server.validadores.autentificacion;
import com.example.Server.modelos.abstracciones.AUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ValidacionCredenciales {
    private final List<IValidadorCredenciales> validadores;
    private final ValidarContrasenna validarContrasenna;

    public void validar(AUsuario credenciales) {
        for (IValidadorCredenciales validador : validadores)
            validador.validar(credenciales);

    }
}

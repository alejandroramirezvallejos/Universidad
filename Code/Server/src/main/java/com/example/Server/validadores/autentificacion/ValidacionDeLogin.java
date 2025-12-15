package com.example.Server.validadores.autentificacion;
import com.example.Server.modelos.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class ValidacionDeLogin implements IValidarLogin {
    private final List<IValidarLogin> validadores;

    @Override
    public String validar(Usuario usuario, String contrasenna) {
        for (IValidarLogin validador : validadores) {
            String error = validador.validar(usuario, contrasenna);

            if (error != null)
                return error;
        }

        return null;
    }
}


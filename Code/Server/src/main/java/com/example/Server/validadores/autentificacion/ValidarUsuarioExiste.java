package com.example.Server.validadores.autentificacion;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.example.Server.modelos.abstracciones.AUsuario;

@Component
@Order(1)
public class ValidarUsuarioExiste implements IValidarLogin {
    @Override
    public String validar(AUsuario usuario, String contrasenna) {
        if (usuario == null)
            return "Usuario no encontrado";

        return null;
    }
}

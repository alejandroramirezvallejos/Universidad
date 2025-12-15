package com.example.Server.validadores.autentificacion;
import com.example.Server.modelos.Usuario;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ValidarContrasenna implements IValidarLogin {
    @Override
    public String validar(Usuario usuario, String contrasenna) {
        if (!contrasenna.equals(usuario.getContrasenna()))
            return "Contraseña incorrecta";

        return null;
    }
}

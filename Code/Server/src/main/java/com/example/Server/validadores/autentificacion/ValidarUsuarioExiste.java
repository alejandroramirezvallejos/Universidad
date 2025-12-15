package com.example.Server.validadores.autentificacion;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.example.Server.modelos.Usuario;

@Component
@Order(1)
public class ValidarUsuarioExiste implements IValidarLogin {
    @Override
    public String validar(Usuario usuario, String contrasenna) {
        if (usuario == null)
            return "Usuario no encontrado";

        return null;
    }
}

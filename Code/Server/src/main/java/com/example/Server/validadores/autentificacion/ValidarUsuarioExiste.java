package com.example.Server.validadores.autentificacion;
import com.example.Server.modelos.abstracciones.AUsuario;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class ValidarUsuarioExiste implements IValidadorCredenciales {
    @Override
    public void validar(AUsuario credenciales) {
        if (credenciales == null)
            throw new RuntimeException("Usuario no encontrado");
    }
}

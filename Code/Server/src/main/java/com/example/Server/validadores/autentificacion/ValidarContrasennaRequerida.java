package com.example.Server.validadores.autentificacion;
import com.example.Server.modelos.abstracciones.AUsuario;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ValidarContrasennaRequerida implements IValidadorCredenciales {
    @Override
    public void validar(AUsuario credenciales) {
        if (credenciales.getContrasenna() == null || credenciales.getContrasenna().trim().isEmpty())
            throw new RuntimeException("La contraseña es requerida");
    }
}

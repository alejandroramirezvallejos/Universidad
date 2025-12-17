package com.example.Server.validadores.autentificacion;
import com.example.Server.modelos.abstracciones.AUsuario;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ValidarEmailRequerido implements IValidadorCredenciales {
    @Override
    public void validar(AUsuario credenciales) {
        if (credenciales.getEmail() == null || credenciales.getEmail().trim().isEmpty())
            throw new RuntimeException("El email es requerido");
    }
}

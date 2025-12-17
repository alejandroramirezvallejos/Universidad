package com.example.Server.validadores.autentificacion;
import org.springframework.stereotype.Component;

@Component
public class ValidarContrasenna implements IValidadorContrasenna {
    @Override
    public void validar(String contrasennaIngresada, String contrasennaAlmacenada) {
        if (contrasennaIngresada == null || !contrasennaIngresada.equals(contrasennaAlmacenada))
            throw new RuntimeException("Contraseña incorrecta");
    }
}

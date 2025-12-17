package com.example.Server.validadores.autentificacion;
import com.example.Server.modelos.abstracciones.AUsuario;

public interface IValidarLogin {
    String validar(AUsuario usuario, String contrasenna);
}


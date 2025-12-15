package com.example.Server.validadores.autentificacion;
import com.example.Server.modelos.Usuario;

public interface IValidarLogin {
    String validar(Usuario usuario, String contrasenna);
}


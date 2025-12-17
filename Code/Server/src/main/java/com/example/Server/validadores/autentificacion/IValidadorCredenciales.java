package com.example.Server.validadores.autentificacion;
import com.example.Server.modelos.abstracciones.AUsuario;

public interface IValidadorCredenciales {
    void validar(AUsuario credenciales);
}



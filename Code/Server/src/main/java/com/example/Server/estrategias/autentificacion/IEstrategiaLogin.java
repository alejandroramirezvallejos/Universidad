package com.example.Server.estrategias.autentificacion;
import com.example.Server.modelos.abstracciones.AUsuario;

public interface IEstrategiaLogin {
    AUsuario login(String email, String contrasenna);
}

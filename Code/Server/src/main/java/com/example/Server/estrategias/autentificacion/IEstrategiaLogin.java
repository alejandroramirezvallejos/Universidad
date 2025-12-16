package com.example.Server.estrategias.autentificacion;
import com.example.Server.modelos.Usuario;

public interface IEstrategiaLogin {
    Usuario login(String email, String contrasenna);
}

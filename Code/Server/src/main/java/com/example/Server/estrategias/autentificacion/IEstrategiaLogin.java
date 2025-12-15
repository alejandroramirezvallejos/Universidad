package com.example.Server.estrategias.autentificacion;
import com.example.Server.dtos.DtoRespuestaLogin;

public interface IEstrategiaLogin {
    DtoRespuestaLogin login(String email, String contrasenna);
}

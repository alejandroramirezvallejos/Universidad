package com.example.Server.estrategias.autentificacion;

import com.example.Server.modelos.abstracciones.AUsuario;
import com.example.Server.modelos.abstracciones.IDirectorCarrera;
import com.example.Server.repositorios.abstracciones.IRepositorioDirectorCarrera;
import com.example.Server.validadores.autentificacion.IValidarLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginDirector implements IEstrategiaLogin {
    @Autowired
    private IRepositorioDirectorCarrera repositorioDirector;
    @Autowired
    private IValidarLogin validadorLogin;

    @Override
    public AUsuario login(String email, String contrasenna) {
        IDirectorCarrera director = repositorioDirector.buscarPorEmail(email);

        if (director == null)
            return null;

        String error = validadorLogin.validar((AUsuario) director, contrasenna);

        if (error != null)
            return null;

        director.setRol("DIRECTOR");
        return (AUsuario) director;
    }
}

package com.example.Server.estrategias.autentificacion;

import com.example.Server.modelos.abstracciones.AUsuario;
import com.example.Server.modelos.abstracciones.IDocente;
import com.example.Server.repositorios.abstracciones.IRepositorioDocente;
import com.example.Server.validadores.autentificacion.IValidarLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginDocente implements IEstrategiaLogin {
    @Autowired
    private IRepositorioDocente repositorioDocente;
    @Autowired
    private IValidarLogin validadorLogin;

    @Override
    public AUsuario login(String email, String contrasenna) {
        IDocente docente = repositorioDocente.buscarPorEmail(email);

        if (docente == null)
            return null;

        String error = validadorLogin.validar((AUsuario) docente, contrasenna);

        if (error != null)
            return null;

        docente.setRol("DOCENTE");
        return (AUsuario) docente;
    }
}

package com.example.Server.estrategias.autentificacion;

import com.example.Server.modelos.abstracciones.AUsuario;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.repositorios.abstracciones.IRepositorioEstudiante;
import com.example.Server.validadores.autentificacion.IValidarLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginEstudiante implements IEstrategiaLogin {
    @Autowired
    private IRepositorioEstudiante repositorioEstudiante;
    @Autowired
    private IValidarLogin validadorLogin;

    @Override
    public AUsuario login(String email, String contrasenna) {
        IEstudiante estudiante = repositorioEstudiante.buscarPorEmail(email);

        if (estudiante == null)
            return null;

        String error = validadorLogin.validar((AUsuario) estudiante, contrasenna);

        if (error != null)
            return null;

        estudiante.setRol("ESTUDIANTE");
        return (AUsuario) estudiante;
    }
}

package com.example.Server.estrategias.autentificacion;

import com.example.Server.casts.CastUsuarioDocente;
import com.example.Server.dtos.DtoRespuestaLogin;
import com.example.Server.modelos.Docente;
import com.example.Server.repositorios.RepositorioDocente;
import com.example.Server.validadores.autentificacion.IValidarLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginDocente implements IEstrategiaLogin {
    @Autowired
    private RepositorioDocente repositorioDocente;
    @Autowired
    private IValidarLogin validadorLogin;
    @Autowired
    private CastUsuarioDocente convertidor;

    @Override
    public DtoRespuestaLogin login(String email, String contrasenna) {
        Docente docente = repositorioDocente.buscarPorEmail(email);

        if (docente == null)
            return null;

        String error = validadorLogin.validar(docente, contrasenna);

        if (error != null)
            return new DtoRespuestaLogin(false, error, null);

        return new DtoRespuestaLogin(true, "Login exitoso", convertidor.getDto(docente));
    }
}

package com.example.Server.estrategias.autentificacion;
import com.example.Server.casts.CastUsuarioDirector;
import com.example.Server.dtos.DtoRespuestaLogin;
import com.example.Server.modelos.DirectorCarrera;
import com.example.Server.repositorios.RepositorioDirectorCarrera;
import com.example.Server.validadores.autentificacion.IValidarLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginDirector implements IEstrategiaLogin {
    @Autowired
    private RepositorioDirectorCarrera repositorioDirector;
    @Autowired
    private IValidarLogin validadorLogin;
    @Autowired
    private CastUsuarioDirector convertidor;

    @Override
    public DtoRespuestaLogin login(String email, String contrasenna) {
        DirectorCarrera director = repositorioDirector.buscarPorEmail(email);

        if (director == null)
            return null;

        String error = validadorLogin.validar(director, contrasenna);

        if (error != null)
            return new DtoRespuestaLogin(false, error, null);

        return new DtoRespuestaLogin(true, "Login exitoso", convertidor.getDto(director));
    }
}

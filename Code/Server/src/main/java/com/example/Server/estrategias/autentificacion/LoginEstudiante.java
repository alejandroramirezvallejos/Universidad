package com.example.Server.estrategias.autentificacion;
import com.example.Server.casts.CastUsuarioEstudiante;
import com.example.Server.dtos.DtoRespuestaLogin;
import com.example.Server.modelos.Estudiante;
import com.example.Server.repositorios.RepositorioEstudiante;
import com.example.Server.validadores.autentificacion.IValidarLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginEstudiante implements IEstrategiaLogin {
    @Autowired
    private RepositorioEstudiante repositorioEstudiante;
    @Autowired
    private IValidarLogin validadorLogin;
    @Autowired
    private CastUsuarioEstudiante convertidor;

    @Override
    public DtoRespuestaLogin login(String email, String contrasenna) {
        Estudiante estudiante = repositorioEstudiante.buscarPorEmail(email);

        if (estudiante == null)
            return null;

        String error = validadorLogin.validar(estudiante, contrasenna);

        if (error != null)
            return new DtoRespuestaLogin(false, error, null);

        return new DtoRespuestaLogin(true, "Login exitoso", convertidor.getDto(estudiante));
    }
}

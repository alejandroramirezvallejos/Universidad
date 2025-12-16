package com.example.Server.estrategias.autentificacion;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Usuario;
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

    @Override
    public Usuario login(String email, String contrasenna) {
        Estudiante estudiante = repositorioEstudiante.buscarPorEmail(email);

        if (estudiante == null)
            return null;

        String error = validadorLogin.validar(estudiante, contrasenna);

        if (error != null)
            return null;

        estudiante.setRol("ESTUDIANTE");
        return estudiante;
    }
}

package com.example.Server.estrategias.autentificacion;
import com.example.Server.modelos.abstracciones.AUsuario;
import com.example.Server.modelos.abstracciones.IDirectorCarrera;
import com.example.Server.repositorios.abstracciones.IRepositorioDirectorCarrera;
import com.example.Server.validadores.autentificacion.ValidacionCredenciales;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginDirector implements IEstrategiaLogin {
    private final IRepositorioDirectorCarrera repositorioDirector;
    private final ValidacionCredenciales validacionCredenciales;

    @Override
    public AUsuario login(String email, String contrasenna) {
        IDirectorCarrera director = repositorioDirector.buscarPorEmail(email);

        if (director == null)
            return null;

        try {
            validacionCredenciales.validarContrasenna(contrasenna, director.getContrasenna());
            director.setRol("DIRECTOR");

            return (AUsuario) director;
        }
        catch (RuntimeException excepcion) {
            return null;
        }
    }
}

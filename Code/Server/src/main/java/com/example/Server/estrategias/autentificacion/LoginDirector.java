package com.example.Server.estrategias.autentificacion;
import com.example.Server.modelos.abstracciones.AUsuario;
import com.example.Server.modelos.abstracciones.IDirectorCarrera;
import com.example.Server.repositorios.abstracciones.IRepositorioDirectorCarrera;
import com.example.Server.validadores.autentificacion.ValidarContrasenna;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginDirector implements IEstrategiaLogin {
    private final IRepositorioDirectorCarrera repositorioDirector;
    private final ValidarContrasenna validarContrasenna;

    @Override
    public AUsuario login(String email, String contrasenna) {
        IDirectorCarrera director = repositorioDirector.buscarPorEmail(email);
        if (director == null)
            return null;

        try {
            validarContrasenna.validar(contrasenna, director.getContrasenna());
            director.setRol("DIRECTOR");

            return (AUsuario) director;
        }
        catch (RuntimeException excepccion) {
            return null;
        }
    }
}

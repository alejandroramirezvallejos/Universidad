package com.example.Server.estrategias.autentificacion;
import com.example.Server.modelos.abstracciones.AUsuario;
import com.example.Server.modelos.abstracciones.IDocente;
import com.example.Server.repositorios.abstracciones.IRepositorioDocente;
import com.example.Server.validadores.autentificacion.ValidacionCredenciales;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginDocente implements IEstrategiaLogin {
    private final IRepositorioDocente repositorioDocente;
    private final ValidacionCredenciales validacionCredenciales;

    @Override
    public AUsuario login(String email, String contrasenna) {
        IDocente docente = repositorioDocente.buscarPorEmail(email);

        if (docente == null)
            return null;

        try {
            validacionCredenciales.validarContrasenna(contrasenna, docente.getContrasenna());
            docente.setRol("DOCENTE");

            return (AUsuario) docente;
        }
        catch (RuntimeException excepcion) {
            return null;
        }
    }
}

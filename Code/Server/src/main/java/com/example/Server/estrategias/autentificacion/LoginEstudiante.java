package com.example.Server.estrategias.autentificacion;

import com.example.Server.modelos.abstracciones.AUsuario;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.repositorios.abstracciones.IRepositorioEstudiante;
import com.example.Server.validadores.autentificacion.ValidarContrasenna;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginEstudiante implements IEstrategiaLogin {
    private final IRepositorioEstudiante repositorioEstudiante;
    private final ValidarContrasenna validarContrasenna;

    @Override
    public AUsuario login(String email, String contrasenna) {
        IEstudiante estudiante = repositorioEstudiante.buscarPorEmail(email);
        if (estudiante == null)
            return null;

        try {
            validarContrasenna.validar(contrasenna, estudiante.getContrasenna());
            estudiante.setRol("ESTUDIANTE");

            return (AUsuario) estudiante;
        }
        catch (RuntimeException excepccion) {
            return null;
        }
    }
}

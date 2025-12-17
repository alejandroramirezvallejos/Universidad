package com.example.Server.estrategias.autentificacion;
import com.example.Server.modelos.abstracciones.AUsuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@AllArgsConstructor
public class ContextoLogin {
    private final List<IEstrategiaLogin> estrategias;

    public AUsuario login(String email, String contrasenna) {
        for (IEstrategiaLogin estrategia : estrategias) {
            AUsuario usuario = estrategia.login(email, contrasenna);

            if (usuario != null)
                return usuario;
        }

        return null;
    }
}

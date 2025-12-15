package com.example.Server.estrategias.autentificacion;
import com.example.Server.dtos.DtoRespuestaLogin;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@AllArgsConstructor
public class ContextoLogin {
    private final List<IEstrategiaLogin> estrategias;

    public DtoRespuestaLogin login(String email, String contrasenna) {
        for (IEstrategiaLogin estrategia : estrategias) {
            DtoRespuestaLogin respuesta = estrategia.login(email, contrasenna);

            if (respuesta != null)
                return respuesta;
        }

        return null;
    }
}

package com.example.Server.estrategias.usuario;
import com.example.Server.modelos.abstracciones.AUsuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class ContextoUsuario {
    private final List<IEstrategiaUsuario> estrategias;

    public AUsuario buscarPorCodigo(String codigo) {
        for (IEstrategiaUsuario estrategia : estrategias) {
            AUsuario usuario = estrategia.buscar(codigo);

            if (usuario != null)
                return usuario;
        }

        return null;
    }

    public AUsuario actualizar(String codigo, Map<String, Object> datos) {
        for (IEstrategiaUsuario estrategia : estrategias) {
            AUsuario usuario = estrategia.actualizar(codigo, datos);

            if (usuario != null)
                return usuario;
        }

        return null;
    }
}

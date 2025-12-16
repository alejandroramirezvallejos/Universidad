package com.example.Server.estrategias.usuario;
import com.example.Server.modelos.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class ContextoUsuario {
    private final List<IEstrategiaUsuario> estrategias;

    public Usuario buscarPorCodigo(String codigo) {
        for (IEstrategiaUsuario estrategia : estrategias) {
            Usuario usuario = estrategia.buscar(codigo);

            if (usuario != null)
                return usuario;
        }

        return null;
    }

    public Usuario actualizar(String codigo, Map<String, Object> datos) {
        for (IEstrategiaUsuario estrategia : estrategias) {
            Usuario usuario = estrategia.actualizar(codigo, datos);

            if (usuario != null)
                return usuario;
        }

        return null;
    }
}

package com.example.Server.estrategias.usuario;
import com.example.Server.dtos.DtoActualizarUsuario;
import com.example.Server.dtos.DtoUsuarioCompleto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@AllArgsConstructor
public class ContextoUsuario {
    private final List<IEstrategiaUsuario> estrategias;

    public DtoUsuarioCompleto buscar(String codigo) {
        for (IEstrategiaUsuario estrategia : estrategias) {
            DtoUsuarioCompleto usuario = estrategia.buscar(codigo);

            if (usuario != null)
                return usuario;
        }

        return null;
    }

    public DtoUsuarioCompleto actualizar(String codigo, DtoActualizarUsuario dto) {
        for (IEstrategiaUsuario estrategia : estrategias) {
            DtoUsuarioCompleto usuario = estrategia.actualizar(codigo, dto);

            if (usuario != null)
                return usuario;
        }

        return null;
    }
}

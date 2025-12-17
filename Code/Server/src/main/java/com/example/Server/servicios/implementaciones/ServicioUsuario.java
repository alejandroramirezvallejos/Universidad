package com.example.Server.servicios.implementaciones;

import com.example.Server.modelos.abstracciones.AUsuario;
import com.example.Server.estrategias.usuario.ContextoUsuario;
import com.example.Server.servicios.abstracciones.IServicioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ServicioUsuario implements IServicioUsuario {
    private final ContextoUsuario contextoUsuario;

    @Override
    public AUsuario getUsuarioPorCodigo(String codigo) {
        AUsuario usuario = contextoUsuario.buscarPorCodigo(codigo);
        if (usuario == null)
            throw new RuntimeException("Usuario no encontrado");
        return usuario;
    }

    @Override
    public AUsuario actualizar(String codigo, Map<String, Object> datos) {
        AUsuario usuario = contextoUsuario.actualizar(codigo, datos);
        if (usuario == null)
            throw new RuntimeException("Usuario no encontrado");
        return usuario;
    }
}

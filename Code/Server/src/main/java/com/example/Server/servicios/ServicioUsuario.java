package com.example.Server.servicios;
import com.example.Server.modelos.Usuario;
import com.example.Server.estrategias.usuario.ContextoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class ServicioUsuario {
    @Autowired
    private ContextoUsuario contextoUsuario;

    public Usuario getUsuarioPorCodigo(String codigo) {
        Usuario usuario = contextoUsuario.buscarPorCodigo(codigo);
        if (usuario == null) throw new RuntimeException("Usuario no encontrado");
        return usuario;
    }

    public Usuario actualizar(String codigo, Map<String, Object> datos) {
        Usuario usuario = contextoUsuario.actualizar(codigo, datos);
        if (usuario == null) throw new RuntimeException("Usuario no encontrado");
        return usuario;
    }
}

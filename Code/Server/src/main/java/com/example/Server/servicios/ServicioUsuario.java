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
        return contextoUsuario.buscarPorCodigo(codigo);
    }

    public Usuario setUsuario(String codigo, Map<String, Object> datos) {
        return contextoUsuario.actualizar(codigo, datos);
    }
}

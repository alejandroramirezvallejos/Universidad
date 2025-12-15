package com.example.Server.servicios;
import com.example.Server.dtos.DtoActualizarUsuario;
import com.example.Server.dtos.DtoUsuarioCompleto;
import com.example.Server.estrategias.usuario.ContextoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuario {
    @Autowired
    private ContextoUsuario contextoUsuario;

    public DtoUsuarioCompleto obtenerPorCodigo(String codigo) {
        return contextoUsuario.buscarPorCodigo(codigo);
    }

    public DtoUsuarioCompleto actualizar(String codigo, DtoActualizarUsuario dto) {
        return contextoUsuario.actualizar(codigo, dto);
    }
}

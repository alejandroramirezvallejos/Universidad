package com.example.Server.estrategias.usuario;
import com.example.Server.dtos.DtoActualizarUsuario;
import com.example.Server.dtos.DtoUsuarioCompleto;

public interface IEstrategiaUsuario {
    DtoUsuarioCompleto buscar(String codigo);
    DtoUsuarioCompleto actualizar(String codigo, DtoActualizarUsuario dto);
}

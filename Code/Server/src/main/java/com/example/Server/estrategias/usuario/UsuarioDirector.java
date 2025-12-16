package com.example.Server.estrategias.usuario;

import com.example.Server.casts.CastUsuarioDirector;
import com.example.Server.dtos.DtoActualizarUsuario;
import com.example.Server.dtos.DtoUsuarioCompleto;
import com.example.Server.modelos.DirectorCarrera;
import com.example.Server.repositorios.RepositorioDirectorCarrera;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDirector implements IEstrategiaUsuario {
    @Autowired
    private RepositorioDirectorCarrera repositorioDirector;
    @Autowired
    private CastUsuarioDirector convertidor;

    @Override
    public DtoUsuarioCompleto buscar(String codigo) {
        DirectorCarrera director = repositorioDirector.buscarPorCodigo(codigo);

        if (director == null)
            return null;

        return convertidor.getDto(director);
    }

    @Override
    public DtoUsuarioCompleto actualizar(String codigo, DtoActualizarUsuario dto) {
        DirectorCarrera director = repositorioDirector.buscarPorCodigo(codigo);

        if (director == null)
            return null;

        BeanUtils.copyProperties(dto, director);

        if (dto.getDepartamento() != null)
            director.setDepartamento(dto.getDepartamento());

        repositorioDirector.guardar(director);

        return convertidor.getDto(director);
    }
}

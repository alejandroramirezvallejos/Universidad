package com.example.Server.estrategias.usuario;

import com.example.Server.casts.CastUsuarioDocente;
import com.example.Server.dtos.DtoActualizarUsuario;
import com.example.Server.dtos.DtoUsuarioCompleto;
import com.example.Server.modelos.Docente;
import com.example.Server.repositorios.RepositorioDocente;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDocente implements IEstrategiaUsuario {
    @Autowired
    private RepositorioDocente repositorioDocente;
    @Autowired
    private CastUsuarioDocente convertidor;

    @Override
    public DtoUsuarioCompleto buscar(String codigo) {
        Docente docente = repositorioDocente.buscarPorCodigo(codigo);

        if (docente == null)
            return null;

        return convertidor.getDto(docente);
    }

    @Override
    public DtoUsuarioCompleto actualizar(String codigo, DtoActualizarUsuario dto) {
        Docente docente = repositorioDocente.buscarPorCodigo(codigo);

        if (docente == null)
            return null;

        BeanUtils.copyProperties(dto, docente);

        if (dto.getDepartamento() != null)
            docente.setDepartamento(dto.getDepartamento());
        if (dto.getEspecialidad() != null)
            docente.setEspecialidad(dto.getEspecialidad());

        repositorioDocente.guardar(docente);

        return convertidor.getDto(docente);
    }
}

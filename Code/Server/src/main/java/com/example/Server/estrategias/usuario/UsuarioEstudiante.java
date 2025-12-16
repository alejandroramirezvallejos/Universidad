package com.example.Server.estrategias.usuario;

import com.example.Server.casts.CastUsuarioEstudiante;
import com.example.Server.dtos.DtoActualizarUsuario;
import com.example.Server.dtos.DtoUsuarioCompleto;
import com.example.Server.modelos.Estudiante;
import com.example.Server.repositorios.RepositorioEstudiante;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioEstudiante implements IEstrategiaUsuario {
    @Autowired
    private RepositorioEstudiante repositorioEstudiante;
    @Autowired
    private CastUsuarioEstudiante convertidor;

    @Override
    public DtoUsuarioCompleto buscar(String codigo) {
        Estudiante estudiante = repositorioEstudiante.buscarPorCodigo(codigo);

        if (estudiante == null)
            return null;

        return convertidor.getDto(estudiante);
    }

    @Override
    public DtoUsuarioCompleto actualizar(String codigo, DtoActualizarUsuario dto) {
        Estudiante estudiante = repositorioEstudiante.buscarPorCodigo(codigo);

        if (estudiante == null)
            return null;

        BeanUtils.copyProperties(dto, estudiante);

        if (dto.getSemestre() != null)
            estudiante.setSemestre(dto.getSemestre());

        repositorioEstudiante.guardar(estudiante);

        return convertidor.getDto(estudiante);
    }
}

package com.example.Server.estrategias.usuario;
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

    @Override
    public DtoUsuarioCompleto buscar(String codigo) {
        DirectorCarrera director = repositorioDirector.buscarPorCodigo(codigo);

        if (director == null)
            return null;

        return castDirector(director);
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

        return castDirector(director);
    }

    private DtoUsuarioCompleto castDirector(DirectorCarrera director) {
        DtoUsuarioCompleto dto = new DtoUsuarioCompleto();
        BeanUtils.copyProperties(director, dto);
        dto.setRol("DIRECTOR");
        dto.setCodigoDirector(director.getCodigo());

        return dto;
    }
}

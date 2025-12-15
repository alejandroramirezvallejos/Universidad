package com.example.Server.estrategias.usuario;
import com.example.Server.dtos.DtoActualizarUsuario;
import com.example.Server.dtos.DtoCarrera;
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

    @Override
    public DtoUsuarioCompleto buscar(String codigo) {
        Estudiante estudiante = repositorioEstudiante.buscarPorCodigo(codigo);

        if (estudiante == null)
            return null;

        return castEstudiante(estudiante);
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

        return castEstudiante(estudiante);
    }

    private DtoUsuarioCompleto castEstudiante(Estudiante estudiante) {
        DtoUsuarioCompleto dto = new DtoUsuarioCompleto();
        BeanUtils.copyProperties(estudiante, dto);
        dto.setRol("ESTUDIANTE");
        dto.setCodigoEstudiante(estudiante.getCodigo());

        if (estudiante.getCarrera() != null) {
            DtoCarrera dtoCarrera = new DtoCarrera();
            dtoCarrera.setCodigo(estudiante.getCarrera().getCodigo());
            dtoCarrera.setNombre(estudiante.getCarrera().getNombre());
            dto.setCarrera(dtoCarrera);
        }

        return dto;
    }
}

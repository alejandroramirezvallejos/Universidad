package com.example.Server.servicios;
import com.example.Server.dtos.DtoActualizarUsuario;
import com.example.Server.dtos.DtoUsuarioCompleto;
import com.example.Server.dtos.DtoCarrera;
import com.example.Server.modelos.*;
import com.example.Server.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuario {
    @Autowired
    private RepositorioEstudiante repositorioEstudiante;
    @Autowired
    private RepositorioDocente repositorioDocente;
    @Autowired
    private RepositorioDirectorCarrera repositorioDirector;

    public DtoUsuarioCompleto obtenerPorCodigo(String codigo) {
        Estudiante estudiante = repositorioEstudiante.buscarPorCodigo(codigo);

        if (estudiante != null)
            return mapearEstudiante(estudiante);

        Docente docente = repositorioDocente.buscarPorCodigo(codigo);

        if (docente != null)
            return mapearDocente(docente);

        DirectorCarrera director = repositorioDirector.buscarPorCodigo(codigo);

        if (director != null)
            return mapearDirector(director);

        return null;
    }

    public DtoUsuarioCompleto actualizar(String codigo, DtoActualizarUsuario dto) {
        Estudiante estudiante = repositorioEstudiante.buscarPorCodigo(codigo);

        if (estudiante != null) {
            actualizarEstudiante(estudiante, dto);
            return mapearEstudiante(estudiante);
        }

        Docente docente = repositorioDocente.buscarPorCodigo(codigo);

        if (docente != null) {
            actualizarDocente(docente, dto);
            return mapearDocente(docente);
        }

        DirectorCarrera director = repositorioDirector.buscarPorCodigo(codigo);

        if (director != null) {
            actualizarDirector(director, dto);
            return mapearDirector(director);
        }

        return null;
    }

    private void actualizarEstudiante(Estudiante estudiante, DtoActualizarUsuario dto) {
        if (dto.getNombre() != null)
            estudiante.setNombre(dto.getNombre());

        if (dto.getApellido() != null)
            estudiante.setApellido(dto.getApellido());

        if (dto.getEmail() != null)
            estudiante.setEmail(dto.getEmail());

        if (dto.getContrasenna() != null && !dto.getContrasenna().isEmpty())
            estudiante.setContrasenna(dto.getContrasenna());

        if (dto.getSemestre() != null)
            estudiante.setSemestre(dto.getSemestre());

        repositorioEstudiante.guardar(estudiante);
    }

    private void actualizarDocente(Docente docente, DtoActualizarUsuario dto) {
        if (dto.getNombre() != null)
            docente.setNombre(dto.getNombre());

        if (dto.getApellido() != null)
            docente.setApellido(dto.getApellido());

        if (dto.getEmail() != null)
            docente.setEmail(dto.getEmail());

        if (dto.getContrasenna() != null && !dto.getContrasenna().isEmpty())
            docente.setContrasenna(dto.getContrasenna());

        if (dto.getDepartamento() != null)
            docente.setDepartamento(dto.getDepartamento());

        if (dto.getEspecialidad() != null)
            docente.setEspecialidad(dto.getEspecialidad());

        repositorioDocente.guardar(docente);
    }

    private void actualizarDirector(DirectorCarrera director, DtoActualizarUsuario dto) {
        if (dto.getNombre() != null)
            director.setNombre(dto.getNombre());

        if (dto.getApellido() != null)
            director.setApellido(dto.getApellido());

        if (dto.getEmail() != null)
            director.setEmail(dto.getEmail());

        if (dto.getContrasenna() != null && !dto.getContrasenna().isEmpty())
            director.setContrasenna(dto.getContrasenna());

        if (dto.getDepartamento() != null)
            director.setDepartamento(dto.getDepartamento());

        repositorioDirector.guardar(director);
    }

    private DtoUsuarioCompleto mapearEstudiante(Estudiante estudiante) {
        DtoUsuarioCompleto dto = new DtoUsuarioCompleto();
        dto.setCodigo(estudiante.getCodigo());
        dto.setNombre(estudiante.getNombre());
        dto.setApellido(estudiante.getApellido());
        dto.setEmail(estudiante.getEmail());
        dto.setRol("ESTUDIANTE");
        dto.setCodigoEstudiante(estudiante.getCodigo());
        dto.setSemestre(estudiante.getSemestre());
        
        if (estudiante.getCarrera() != null) {
            DtoCarrera dtoCarrera = new DtoCarrera();
            dtoCarrera.setCodigo(estudiante.getCarrera().getCodigo());
            dtoCarrera.setNombre(estudiante.getCarrera().getNombre());
            dto.setCarrera(dtoCarrera);
        }
        
        return dto;
    }

    private DtoUsuarioCompleto mapearDocente(Docente docente) {
        DtoUsuarioCompleto dto = new DtoUsuarioCompleto();
        dto.setCodigo(docente.getCodigo());
        dto.setNombre(docente.getNombre());
        dto.setApellido(docente.getApellido());
        dto.setEmail(docente.getEmail());
        dto.setRol("DOCENTE");
        dto.setCodigoDocente(docente.getCodigo());
        dto.setDepartamento(docente.getDepartamento());
        dto.setEspecialidad(docente.getEspecialidad());
        return dto;
    }

    private DtoUsuarioCompleto mapearDirector(DirectorCarrera director) {
        DtoUsuarioCompleto dto = new DtoUsuarioCompleto();
        dto.setCodigo(director.getCodigo());
        dto.setNombre(director.getNombre());
        dto.setApellido(director.getApellido());
        dto.setEmail(director.getEmail());
        dto.setRol("DIRECTOR");
        dto.setCodigoDirector(director.getCodigo());
        dto.setDepartamento(director.getDepartamento());
        return dto;
    }
}

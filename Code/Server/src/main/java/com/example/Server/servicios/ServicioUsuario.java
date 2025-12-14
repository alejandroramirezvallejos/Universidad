package com.example.Server.servicios;

import com.example.Server.dtos.DtoActualizarUsuario;
import com.example.Server.dtos.DtoUsuarioCompleto;
import com.example.Server.dtos.DtoCarrera;
import com.example.Server.modelos.*;
import com.example.Server.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio para gestión de usuarios (perfil)
 * Maneja obtención y actualización de datos de usuario
 */
@Service
public class ServicioUsuario {

    @Autowired
    private RepositorioEstudiante repositorioEstudiante;

    @Autowired
    private RepositorioDocente repositorioDocente;

    @Autowired
    private RepositorioDirectorCarrera repositorioDirector;

    /**
     * Obtiene los datos completos de un usuario por su código
     */
    public DtoUsuarioCompleto obtenerPorCodigo(String codigo) {
        // Buscar en estudiantes
        Estudiante estudiante = repositorioEstudiante.buscarPorCodigo(codigo);
        if (estudiante != null) {
            return mapearEstudiante(estudiante);
        }

        // Buscar en docentes
        Docente docente = repositorioDocente.buscarPorCodigo(codigo);
        if (docente != null) {
            return mapearDocente(docente);
        }

        // Buscar en directores
        DirectorCarrera director = repositorioDirector.buscarPorCodigo(codigo);
        if (director != null) {
            return mapearDirector(director);
        }

        return null;
    }

    /**
     * Actualiza los datos de un usuario
     */
    public DtoUsuarioCompleto actualizar(String codigo, DtoActualizarUsuario dto) {
        // Buscar en estudiantes
        Estudiante estudiante = repositorioEstudiante.buscarPorCodigo(codigo);
        if (estudiante != null) {
            actualizarEstudiante(estudiante, dto);
            return mapearEstudiante(estudiante);
        }

        // Buscar en docentes
        Docente docente = repositorioDocente.buscarPorCodigo(codigo);
        if (docente != null) {
            actualizarDocente(docente, dto);
            return mapearDocente(docente);
        }

        // Buscar en directores
        DirectorCarrera director = repositorioDirector.buscarPorCodigo(codigo);
        if (director != null) {
            actualizarDirector(director, dto);
            return mapearDirector(director);
        }

        return null;
    }

    /**
     * Actualiza datos de estudiante
     */
    private void actualizarEstudiante(Estudiante estudiante, DtoActualizarUsuario dto) {
        if (dto.getNombre() != null) {
            estudiante.setNombre(dto.getNombre());
        }
        if (dto.getApellido() != null) {
            estudiante.setApellido(dto.getApellido());
        }
        if (dto.getEmail() != null) {
            estudiante.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            estudiante.setPassword(dto.getPassword());
        }
        if (dto.getSemestre() != null) {
            estudiante.setSemestre(dto.getSemestre());
        }
        repositorioEstudiante.guardar(estudiante);
    }

    /**
     * Actualiza datos de docente
     */
    private void actualizarDocente(Docente docente, DtoActualizarUsuario dto) {
        if (dto.getNombre() != null) {
            docente.setNombre(dto.getNombre());
        }
        if (dto.getApellido() != null) {
            docente.setApellido(dto.getApellido());
        }
        if (dto.getEmail() != null) {
            docente.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            docente.setPassword(dto.getPassword());
        }
        if (dto.getDepartamento() != null) {
            docente.setDepartamento(dto.getDepartamento());
        }
        if (dto.getEspecialidad() != null) {
            docente.setEspecialidad(dto.getEspecialidad());
        }
        repositorioDocente.guardar(docente);
    }

    /**
     * Actualiza datos de director
     */
    private void actualizarDirector(DirectorCarrera director, DtoActualizarUsuario dto) {
        if (dto.getNombre() != null) {
            director.setNombre(dto.getNombre());
        }
        if (dto.getApellido() != null) {
            director.setApellido(dto.getApellido());
        }
        if (dto.getEmail() != null) {
            director.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            director.setPassword(dto.getPassword());
        }
        if (dto.getDepartamento() != null) {
            director.setDepartamento(dto.getDepartamento());
        }
        repositorioDirector.guardar(director);
    }

    /**
     * Mapea Estudiante a DtoUsuarioCompleto
     */
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

    /**
     * Mapea Docente a DtoUsuarioCompleto
     */
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

    /**
     * Mapea DirectorCarrera a DtoUsuarioCompleto
     */
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

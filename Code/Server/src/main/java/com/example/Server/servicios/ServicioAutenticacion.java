package com.example.Server.servicios;

import com.example.Server.dtos.*;
import com.example.Server.modelos.*;
import com.example.Server.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de Autenticación SIMPLE (sin JWT)
 * Maneja login y registro de usuarios
 */
@Service
public class ServicioAutenticacion {

    @Autowired
    private RepositorioEstudiante repositorioEstudiante;

    @Autowired
    private RepositorioDocente repositorioDocente;

    @Autowired
    private RepositorioDirectorCarrera repositorioDirector;

    @Autowired
    private RepositorioCarrera repositorioCarrera;

    /**
     * Login simple - verifica email y password
     */
    public DtoRespuestaLogin login(DtoCredenciales credenciales) {
        String email = credenciales.getEmail();
        String password = credenciales.getPassword();

        // Validar campos vacíos
        if (email == null || email.trim().isEmpty()) {
            return new DtoRespuestaLogin(false, "El email es requerido", null);
        }
        if (password == null || password.trim().isEmpty()) {
            return new DtoRespuestaLogin(false, "La contraseña es requerida", null);
        }

        // Buscar en estudiantes
        Estudiante estudiante = repositorioEstudiante.buscarPorEmail(email);
        if (estudiante != null && password.equals(estudiante.getPassword())) {
            DtoUsuarioCompleto usuario = mapearEstudiante(estudiante);
            return new DtoRespuestaLogin(true, "Login exitoso", usuario);
        }

        // Buscar en docentes
        Docente docente = repositorioDocente.buscarPorEmail(email);
        if (docente != null && password.equals(docente.getPassword())) {
            DtoUsuarioCompleto usuario = mapearDocente(docente);
            return new DtoRespuestaLogin(true, "Login exitoso", usuario);
        }

        // Buscar en directores
        DirectorCarrera director = repositorioDirector.buscarPorEmail(email);
        if (director != null && password.equals(director.getPassword())) {
            DtoUsuarioCompleto usuario = mapearDirector(director);
            return new DtoRespuestaLogin(true, "Login exitoso", usuario);
        }

        // Credenciales inválidas
        return new DtoRespuestaLogin(false, "Email o contraseña incorrectos", null);
    }

    /**
     * Registro de estudiante
     */
    public DtoRespuestaRegistro registrarEstudiante(DtoRegistroEstudiante dto) {
        // Validar datos
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
            return new DtoRespuestaRegistro(false, "El email es requerido", null);
        }
        if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
            return new DtoRespuestaRegistro(false, "La contraseña es requerida", null);
        }
        if (dto.getCodigoEstudiante() == null || dto.getCodigoEstudiante().trim().isEmpty()) {
            return new DtoRespuestaRegistro(false, "El código de estudiante es requerido", null);
        }

        // Verificar que el email no exista
        if (emailYaExiste(dto.getEmail())) {
            return new DtoRespuestaRegistro(false, "El email ya está registrado", null);
        }

        // Verificar que el código no exista
        if (repositorioEstudiante.buscarPorCodigo(dto.getCodigoEstudiante()) != null) {
            return new DtoRespuestaRegistro(false, "El código de estudiante ya existe", null);
        }

        // Buscar carrera
        Carrera carrera = repositorioCarrera.buscarPorCodigo(dto.getCodigoCarrera());
        if (carrera == null) {
            return new DtoRespuestaRegistro(false, "La carrera no existe", null);
        }

        // Crear estudiante
        Estudiante estudiante = new Estudiante();
        estudiante.setCodigo(dto.getCodigoEstudiante());
        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());
        estudiante.setEmail(dto.getEmail());
        estudiante.setPassword(dto.getPassword()); // En producción, usar hash
        estudiante.setCarrera(carrera);
        estudiante.setSemestre(dto.getSemestre() != null ? dto.getSemestre() : 1);

        Estudiante guardado = repositorioEstudiante.guardar(estudiante);
        DtoUsuarioCompleto usuario = mapearEstudiante(guardado);

        return new DtoRespuestaRegistro(true, "Estudiante registrado exitosamente", usuario);
    }

    /**
     * Registro de docente
     */
    public DtoRespuestaRegistro registrarDocente(DtoRegistroDocente dto) {
        // Validar datos
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
            return new DtoRespuestaRegistro(false, "El email es requerido", null);
        }
        if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
            return new DtoRespuestaRegistro(false, "La contraseña es requerida", null);
        }
        if (dto.getCodigoDocente() == null || dto.getCodigoDocente().trim().isEmpty()) {
            return new DtoRespuestaRegistro(false, "El código de docente es requerido", null);
        }

        // Verificar que el email no exista
        if (emailYaExiste(dto.getEmail())) {
            return new DtoRespuestaRegistro(false, "El email ya está registrado", null);
        }

        // Verificar que el código no exista
        if (repositorioDocente.buscarPorCodigo(dto.getCodigoDocente()) != null) {
            return new DtoRespuestaRegistro(false, "El código de docente ya existe", null);
        }

        // Crear docente
        Docente docente = new Docente();
        docente.setCodigo(dto.getCodigoDocente());
        docente.setNombre(dto.getNombre());
        docente.setApellido(dto.getApellido());
        docente.setEmail(dto.getEmail());
        docente.setPassword(dto.getPassword()); // En producción, usar hash
        docente.setDepartamento(dto.getDepartamento());
        docente.setEspecialidad(dto.getEspecialidad());

        Docente guardado = repositorioDocente.guardar(docente);
        DtoUsuarioCompleto usuario = mapearDocente(guardado);

        return new DtoRespuestaRegistro(true, "Docente registrado exitosamente", usuario);
    }

    /**
     * Verifica si un email ya existe en cualquier rol
     */
    private boolean emailYaExiste(String email) {
        return repositorioEstudiante.buscarPorEmail(email) != null ||
               repositorioDocente.buscarPorEmail(email) != null ||
               repositorioDirector.buscarPorEmail(email) != null;
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

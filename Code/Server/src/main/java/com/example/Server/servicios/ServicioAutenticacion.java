package com.example.Server.servicios;
import com.example.Server.dtos.*;
import com.example.Server.modelos.*;
import com.example.Server.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public DtoRespuestaLogin login(DtoCredenciales credenciales) {
        String email = credenciales.getEmail();
        String contrasenna = credenciales.getContrasenna();

        if (email == null || email.trim().isEmpty())
            return new DtoRespuestaLogin(false, "El email es requerido", null);

        if (contrasenna == null || contrasenna.trim().isEmpty())
            return new DtoRespuestaLogin(false, "La contraseña es requerida", null);

        Estudiante estudiante = repositorioEstudiante.buscarPorEmail(email);

        if (estudiante != null && contrasenna.equals(estudiante.getContrasenna())) {
            DtoUsuarioCompleto usuario = mapearEstudiante(estudiante);
            return new DtoRespuestaLogin(true, "Login exitoso", usuario);
        }

        Docente docente = repositorioDocente.buscarPorEmail(email);

        if (docente != null && contrasenna.equals(docente.getContrasenna())) {
            DtoUsuarioCompleto usuario = mapearDocente(docente);
            return new DtoRespuestaLogin(true, "Login exitoso", usuario);
        }

        DirectorCarrera director = repositorioDirector.buscarPorEmail(email);

        if (director != null && contrasenna.equals(director.getContrasenna())) {
            DtoUsuarioCompleto usuario = mapearDirector(director);
            return new DtoRespuestaLogin(true, "Login exitoso", usuario);
        }

        return new DtoRespuestaLogin(false, "Email o contraseña incorrectos", null);
    }

    public DtoRespuestaRegistro registrarEstudiante(DtoRegistroEstudiante dto) {
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty())
            return new DtoRespuestaRegistro(false, "El email es requerido", null);

        if (dto.getContrasenna() == null || dto.getContrasenna().trim().isEmpty())
            return new DtoRespuestaRegistro(false, "La contraseña es requerida", null);

        if (dto.getCodigoEstudiante() == null || dto.getCodigoEstudiante().trim().isEmpty())
            return new DtoRespuestaRegistro(false, "El código de estudiante es requerido", null);

        if (emailYaExiste(dto.getEmail()))
            return new DtoRespuestaRegistro(false, "El email ya está registrado", null);

        if (repositorioEstudiante.buscarPorCodigo(dto.getCodigoEstudiante()) != null)
            return new DtoRespuestaRegistro(false, "El código de estudiante ya existe", null);

        Carrera carrera = repositorioCarrera.buscarPorCodigo(dto.getCodigoCarrera());

        if (carrera == null)
            return new DtoRespuestaRegistro(false, "La carrera no existe", null);

        Estudiante estudiante = new Estudiante();
        estudiante.setCodigo(dto.getCodigoEstudiante());
        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());
        estudiante.setEmail(dto.getEmail());
        estudiante.setContrasenna(dto.getContrasenna());
        estudiante.setCarrera(carrera);
        estudiante.setSemestre(dto.getSemestre() != null ? dto.getSemestre() : 1);
        Estudiante guardado = repositorioEstudiante.guardar(estudiante);
        DtoUsuarioCompleto usuario = mapearEstudiante(guardado);

        return new DtoRespuestaRegistro(true, "Estudiante registrado exitosamente", usuario);
    }

    public DtoRespuestaRegistro registrarDocente(DtoRegistroDocente dto) {
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty())
            return new DtoRespuestaRegistro(false, "El email es requerido", null);

        if (dto.getContrasenna() == null || dto.getContrasenna().trim().isEmpty())
            return new DtoRespuestaRegistro(false, "La contraseña es requerida", null);

        if (dto.getCodigoDocente() == null || dto.getCodigoDocente().trim().isEmpty())
            return new DtoRespuestaRegistro(false, "El código de docente es requerido", null);

        if (emailYaExiste(dto.getEmail()))
            return new DtoRespuestaRegistro(false, "El email ya está registrado", null);

        if (repositorioDocente.buscarPorCodigo(dto.getCodigoDocente()) != null)
            return new DtoRespuestaRegistro(false, "El código de docente ya existe", null);

        Docente docente = new Docente();
        docente.setCodigo(dto.getCodigoDocente());
        docente.setNombre(dto.getNombre());
        docente.setApellido(dto.getApellido());
        docente.setEmail(dto.getEmail());
        docente.setContrasenna(dto.getContrasenna());
        docente.setDepartamento(dto.getDepartamento());
        docente.setEspecialidad(dto.getEspecialidad());
        Docente guardado = repositorioDocente.guardar(docente);
        DtoUsuarioCompleto usuario = mapearDocente(guardado);

        return new DtoRespuestaRegistro(true, "Docente registrado exitosamente", usuario);
    }

    private boolean emailYaExiste(String email) {
        return repositorioEstudiante.buscarPorEmail(email) != null ||
               repositorioDocente.buscarPorEmail(email) != null ||
               repositorioDirector.buscarPorEmail(email) != null;
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

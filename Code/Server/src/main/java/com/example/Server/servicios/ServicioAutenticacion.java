package com.example.Server.servicios;
import com.example.Server.dtos.*;
import com.example.Server.modelos.*;
import com.example.Server.repositorios.*;
 import com.example.Server.estrategias.autentificacion.ContextoLogin;
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
    @Autowired
    private ContextoLogin contextoLogin;

    public DtoRespuestaLogin login(DtoCredenciales credenciales) {
        String email = credenciales.getEmail();
        String contrasenna = credenciales.getContrasenna();

        if (email == null || email.trim().isEmpty())
            return new DtoRespuestaLogin(false, "El email es requerido", null);

        if (contrasenna == null || contrasenna.trim().isEmpty())
            return new DtoRespuestaLogin(false, "La contraseña es requerida", null);

        DtoRespuestaLogin respuesta = contextoLogin.login(email, contrasenna);
        if (respuesta != null)
            return respuesta;

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

        DtoUsuarioCompleto usuario = new DtoUsuarioCompleto();
        usuario.setCodigo(guardado.getCodigo());
        usuario.setNombre(guardado.getNombre());
        usuario.setApellido(guardado.getApellido());
        usuario.setEmail(guardado.getEmail());
        usuario.setRol("ESTUDIANTE");
        usuario.setCodigoEstudiante(guardado.getCodigo());
        usuario.setSemestre(guardado.getSemestre());
        if (guardado.getCarrera() != null) {
            DtoCarrera dtoCarrera = new DtoCarrera();
            dtoCarrera.setCodigo(guardado.getCarrera().getCodigo());
            dtoCarrera.setNombre(guardado.getCarrera().getNombre());
            usuario.setCarrera(dtoCarrera);
        }

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

        DtoUsuarioCompleto usuario = new DtoUsuarioCompleto();
        usuario.setCodigo(guardado.getCodigo());
        usuario.setNombre(guardado.getNombre());
        usuario.setApellido(guardado.getApellido());
        usuario.setEmail(guardado.getEmail());
        usuario.setRol("DOCENTE");
        usuario.setCodigoDocente(guardado.getCodigo());
        usuario.setDepartamento(guardado.getDepartamento());
        usuario.setEspecialidad(guardado.getEspecialidad());

        return new DtoRespuestaRegistro(true, "Docente registrado exitosamente", usuario);
    }

    private boolean emailYaExiste(String email) {
        return repositorioEstudiante.buscarPorEmail(email) != null ||
               repositorioDocente.buscarPorEmail(email) != null ||
               repositorioDirector.buscarPorEmail(email) != null;
    }
}

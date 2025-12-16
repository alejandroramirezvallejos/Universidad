package com.example.Server.servicios;
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

    public Usuario login(Usuario credenciales) {
        String email = credenciales.getEmail();
        String contrasenna = credenciales.getContrasenna();

        if (email == null || email.trim().isEmpty())
            throw new RuntimeException("El email es requerido");

        if (contrasenna == null || contrasenna.trim().isEmpty())
            throw new RuntimeException("La contraseña es requerida");

        Usuario usuario = contextoLogin.login(email, contrasenna);

        if (usuario != null)
            return usuario;

        throw new RuntimeException("Email o contraseña incorrectos");
    }

    public Estudiante registrarEstudiante(Estudiante estudiante) {
        if (estudiante.getEmail() == null || estudiante.getEmail().trim().isEmpty())
            throw new RuntimeException("El email es requerido");

        if (estudiante.getContrasenna() == null || estudiante.getContrasenna().trim().isEmpty())
            throw new RuntimeException("La contraseña es requerida");

        if (estudiante.getCodigo() == null || estudiante.getCodigo().trim().isEmpty())
            throw new RuntimeException("El código de estudiante es requerido");

        if (emailYaExiste(estudiante.getEmail()))
            throw new RuntimeException("El email ya está registrado");

        if (repositorioEstudiante.buscarPorCodigo(estudiante.getCodigo()) != null)
            throw new RuntimeException("El código de estudiante ya existe");

        if (estudiante.getCarrera() != null && estudiante.getCarrera().getCodigo() != null) {
             Carrera carrera = repositorioCarrera.buscarPorCodigo(estudiante.getCarrera().getCodigo());
             if (carrera == null)
                 throw new RuntimeException("La carrera no existe");
             estudiante.setCarrera(carrera);
        }
        else
             throw new RuntimeException("La carrera es requerida");


        if (estudiante.getSemestre() == 0)
            estudiante.setSemestre(1);

        return repositorioEstudiante.guardar(estudiante);
    }

    public Docente registrarDocente(Docente docente) {
        if (docente.getEmail() == null || docente.getEmail().trim().isEmpty())
            throw new RuntimeException("El email es requerido");

        if (docente.getContrasenna() == null || docente.getContrasenna().trim().isEmpty())
            throw new RuntimeException("La contraseña es requerida");

        if (docente.getCodigo() == null || docente.getCodigo().trim().isEmpty())
            throw new RuntimeException("El código de docente es requerido");

        if (emailYaExiste(docente.getEmail()))
            throw new RuntimeException("El email ya está registrado");

        if (repositorioDocente.buscarPorCodigo(docente.getCodigo()) != null)
            throw new RuntimeException("El código de docente ya existe");

        return repositorioDocente.guardar(docente);
    }

    private boolean emailYaExiste(String email) {
        return repositorioEstudiante.buscarPorEmail(email) != null ||
               repositorioDocente.buscarPorEmail(email) != null ||
               repositorioDirector.buscarPorEmail(email) != null;
    }
}

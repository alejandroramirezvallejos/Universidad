package com.example.Server.servicios.implementaciones;

import com.example.Server.modelos.abstracciones.AUsuario;
import com.example.Server.modelos.abstracciones.ICarrera;
import com.example.Server.modelos.abstracciones.IDocente;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.repositorios.abstracciones.IRepositorioCarrera;
import com.example.Server.repositorios.abstracciones.IRepositorioDirectorCarrera;
import com.example.Server.repositorios.abstracciones.IRepositorioDocente;
import com.example.Server.repositorios.abstracciones.IRepositorioEstudiante;
import com.example.Server.estrategias.autentificacion.ContextoLogin;
import com.example.Server.servicios.abstracciones.IServicioAutenticacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicioAutenticacion implements IServicioAutenticacion {
    private final IRepositorioEstudiante repositorioEstudiante;
    private final IRepositorioDocente repositorioDocente;
    private final IRepositorioDirectorCarrera repositorioDirector;
    private final IRepositorioCarrera repositorioCarrera;
    private final ContextoLogin contextoLogin;

    @Override
    public AUsuario login(AUsuario credenciales) {
        validarCredenciales(credenciales);
        AUsuario usuario = contextoLogin.login(credenciales.getEmail(), credenciales.getContrasenna());
        if (usuario == null)
            throw new RuntimeException("Email o contraseña incorrectos");
        return usuario;
    }

    @Override
    public IEstudiante registrarEstudiante(IEstudiante estudiante) {
        validarDatosEstudiante(estudiante);
        validarEmailUnico(estudiante.getEmail());
        validarCodigoEstudianteUnico(estudiante.getCodigo());
        ICarrera carrera = validarCarrera(estudiante);
        estudiante.setCarrera(carrera);
        if (estudiante.getSemestre() == 0)
            estudiante.setSemestre(1);
        return repositorioEstudiante.guardar(estudiante);
    }

    @Override
    public IDocente registrarDocente(IDocente docente) {
        validarDatosDocente(docente);
        validarEmailUnico(docente.getEmail());
        validarCodigoDocenteUnico(docente.getCodigo());
        return repositorioDocente.guardar(docente);
    }

    private void validarCredenciales(AUsuario credenciales) {
        if (credenciales.getEmail() == null || credenciales.getEmail().trim().isEmpty())
            throw new RuntimeException("El email es requerido");
        if (credenciales.getContrasenna() == null || credenciales.getContrasenna().trim().isEmpty())
            throw new RuntimeException("La contraseña es requerida");
    }

    private void validarDatosEstudiante(IEstudiante estudiante) {
        if (estudiante.getEmail() == null || estudiante.getEmail().trim().isEmpty())
            throw new RuntimeException("El email es requerido");
        if (estudiante.getContrasenna() == null || estudiante.getContrasenna().trim().isEmpty())
            throw new RuntimeException("La contraseña es requerida");
        if (estudiante.getCodigo() == null || estudiante.getCodigo().trim().isEmpty())
            throw new RuntimeException("El código de estudiante es requerido");
    }

    private void validarDatosDocente(IDocente docente) {
        if (docente.getEmail() == null || docente.getEmail().trim().isEmpty())
            throw new RuntimeException("El email es requerido");
        if (docente.getContrasenna() == null || docente.getContrasenna().trim().isEmpty())
            throw new RuntimeException("La contraseña es requerida");
        if (docente.getCodigo() == null || docente.getCodigo().trim().isEmpty())
            throw new RuntimeException("El código de docente es requerido");
    }

    private void validarEmailUnico(String email) {
        if (repositorioEstudiante.buscarPorEmail(email) != null ||
            repositorioDocente.buscarPorEmail(email) != null ||
            repositorioDirector.buscarPorEmail(email) != null)
            throw new RuntimeException("El email ya está registrado");
    }

    private void validarCodigoEstudianteUnico(String codigo) {
        if (repositorioEstudiante.buscarPorCodigo(codigo) != null)
            throw new RuntimeException("El código de estudiante ya existe");
    }

    private void validarCodigoDocenteUnico(String codigo) {
        if (repositorioDocente.buscarPorCodigo(codigo) != null)
            throw new RuntimeException("El código de docente ya existe");
    }

    private ICarrera validarCarrera(IEstudiante estudiante) {
        if (estudiante.getCarrera() == null || estudiante.getCarrera().getCodigo() == null)
            throw new RuntimeException("La carrera es requerida");
        ICarrera carrera = repositorioCarrera.buscarPorCodigo(estudiante.getCarrera().getCodigo());
        if (carrera == null)
            throw new RuntimeException("La carrera no existe");
        return carrera;
    }
}

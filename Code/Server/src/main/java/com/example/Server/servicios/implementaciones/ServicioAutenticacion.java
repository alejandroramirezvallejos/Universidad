package com.example.Server.servicios.implementaciones;
import com.example.Server.modelos.abstracciones.AUsuario;
import com.example.Server.modelos.abstracciones.IDocente;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.repositorios.abstracciones.IRepositorioDocente;
import com.example.Server.repositorios.abstracciones.IRepositorioEstudiante;
import com.example.Server.estrategias.autentificacion.ContextoLogin;
import com.example.Server.servicios.abstracciones.IServicioAutenticacion;
import com.example.Server.validadores.autentificacion.ValidacionCredenciales;
import com.example.Server.validadores.registro.docentes.ValidacionRegistroDocente;
import com.example.Server.validadores.registro.estudiantes.ValidacionRegistroEstudiante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicioAutenticacion implements IServicioAutenticacion {
    private final IRepositorioEstudiante repositorioEstudiante;
    private final IRepositorioDocente repositorioDocente;
    private final ContextoLogin contextoLogin;
    private final ValidacionCredenciales validacionCredenciales;
    private final ValidacionRegistroEstudiante validacionRegistroEstudiante;
    private final ValidacionRegistroDocente validacionRegistroDocente;

    @Override
    public AUsuario login(AUsuario credenciales) {
        validacionCredenciales.validar(credenciales);
        AUsuario usuario = contextoLogin.login(credenciales.getEmail(), credenciales.getContrasenna());

        if (usuario == null)
            throw new RuntimeException("Email o contraseña incorrectos");

        return usuario;
    }

    @Override
    public IEstudiante registrarEstudiante(IEstudiante estudiante) {
        validacionRegistroEstudiante.validar(estudiante);

        if (estudiante.getSemestre() == 0)
            estudiante.setSemestre(1);

        return repositorioEstudiante.guardar(estudiante);
    }

    @Override
    public IDocente registrarDocente(IDocente docente) {
        validacionRegistroDocente.validar(docente);
        return repositorioDocente.guardar(docente);
    }
}

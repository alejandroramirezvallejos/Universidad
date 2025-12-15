package com.example.Server.estrategias.autentificacion;
import com.example.Server.dtos.DtoCarrera;
import com.example.Server.dtos.DtoRespuestaLogin;
import com.example.Server.dtos.DtoUsuarioCompleto;
import com.example.Server.modelos.Estudiante;
import com.example.Server.repositorios.RepositorioEstudiante;
import com.example.Server.validadores.autentificacion.IValidarLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginEstudiante implements IEstrategiaLogin {
    @Autowired
    private RepositorioEstudiante repositorioEstudiante;
    @Autowired
    private IValidarLogin validadorLogin;

    @Override
    public DtoRespuestaLogin login(String email, String contrasenna) {
        Estudiante estudiante = repositorioEstudiante.buscarPorEmail(email);

        if (estudiante == null)
            return null;

        String error = validadorLogin.validar(estudiante, contrasenna);

        if (error != null)
            return new DtoRespuestaLogin(false, error, null);

        return new DtoRespuestaLogin(true, "Login exitoso", castEstudiante(estudiante));
    }

    private DtoUsuarioCompleto castEstudiante(Estudiante estudiante) {
        DtoUsuarioCompleto dto = new DtoUsuarioCompleto();

        dto.setCodigo(estudiante.getCodigo());
        dto.setNombre(estudiante.getNombre());
        dto.setApellido(estudiante.getApellido());
        dto.setEmail(estudiante.getEmail());
        dto.setRol("ESTUDIANTE");
        dto.setCodigoEstudiante(estudiante.getCodigo());
        dto.setSemestre(estudiante.getSemestre());

        castCarrera(estudiante, dto);
        return dto;
    }

    private void castCarrera(Estudiante estudiante, DtoUsuarioCompleto dto) {
        if (estudiante.getCarrera() != null) {
            DtoCarrera dtoCarrera = new DtoCarrera();
            dtoCarrera.setCodigo(estudiante.getCarrera().getCodigo());
            dtoCarrera.setNombre(estudiante.getCarrera().getNombre());
            dto.setCarrera(dtoCarrera);
        }
    }
}

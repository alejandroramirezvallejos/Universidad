package com.example.Server.estrategias.autentificacion;
import com.example.Server.dtos.DtoCarrera;
import com.example.Server.dtos.DtoRespuestaLogin;
import com.example.Server.dtos.DtoUsuarioCompleto;
import com.example.Server.modelos.DirectorCarrera;
import com.example.Server.repositorios.RepositorioDirectorCarrera;
import com.example.Server.validadores.autentificacion.IValidarLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginDirector implements IEstrategiaLogin {
    @Autowired
    private RepositorioDirectorCarrera repositorioDirector;
    @Autowired
    private IValidarLogin validadorLogin;

    @Override
    public DtoRespuestaLogin login(String email, String contrasenna) {
        DirectorCarrera director = repositorioDirector.buscarPorEmail(email);

        if (director == null)
            return null;

        String error = validadorLogin.validar(director, contrasenna);

        if (error != null)
            return new DtoRespuestaLogin(false, error, null);

        return new DtoRespuestaLogin(true, "Login exitoso", castDirector(director));
    }

    private DtoUsuarioCompleto castDirector(DirectorCarrera director) {
        DtoUsuarioCompleto dto = new DtoUsuarioCompleto();

        dto.setCodigo(director.getCodigo());
        dto.setNombre(director.getNombre());
        dto.setApellido(director.getApellido());
        dto.setEmail(director.getEmail());
        dto.setRol("DIRECTOR");
        dto.setCodigoDirector(director.getCodigo());
        dto.setDepartamento(director.getDepartamento());
        castCarrera(director, dto);

        return dto;
    }

    private void castCarrera(DirectorCarrera director, DtoUsuarioCompleto dto) {
        if (director.getCarrera() != null) {
            DtoCarrera dtoCarrera = new DtoCarrera();
            dtoCarrera.setCodigo(director.getCarrera().getCodigo());
            dtoCarrera.setNombre(director.getCarrera().getNombre());
            dto.setCarrera(dtoCarrera);
        }
    }
}

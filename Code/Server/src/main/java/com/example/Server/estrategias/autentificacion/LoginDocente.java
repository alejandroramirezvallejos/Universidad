package com.example.Server.estrategias.autentificacion;
import com.example.Server.dtos.DtoRespuestaLogin;
import com.example.Server.dtos.DtoUsuarioCompleto;
import com.example.Server.modelos.Docente;
import com.example.Server.repositorios.RepositorioDocente;
import com.example.Server.validadores.autentificacion.IValidarLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginDocente implements IEstrategiaLogin {
    @Autowired
    private RepositorioDocente repositorioDocente;
    @Autowired
    private IValidarLogin validadorLogin;

    @Override
    public DtoRespuestaLogin login(String email, String contrasenna) {
        Docente docente = repositorioDocente.buscarPorEmail(email);

        if (docente == null)
            return null;

        String error = validadorLogin.validar(docente, contrasenna);

        if (error != null)
            return new DtoRespuestaLogin(false, error, null);

        return new DtoRespuestaLogin(true, "Login exitoso", castDocente(docente));
    }

    private DtoUsuarioCompleto castDocente(Docente docente) {
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
}

package com.example.Server.estrategias.usuario;
import com.example.Server.modelos.abstracciones.AUsuario;
import com.example.Server.modelos.abstracciones.IDirectorCarrera;
import com.example.Server.repositorios.abstracciones.IRepositorioDirectorCarrera;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UsuarioDirector implements IEstrategiaUsuario {
    private final IRepositorioDirectorCarrera repositorioDirector;

    @Override
    public AUsuario buscar(String codigo) {
        return (AUsuario) repositorioDirector.buscarPorCodigo(codigo);
    }

    @Override
    public AUsuario actualizar(String codigo, Map<String, Object> datos) {
        IDirectorCarrera director = repositorioDirector.buscarPorCodigo(codigo);

        if (director == null)
            return null;

        if (datos.containsKey("nombre"))
            director.setNombre((String) datos.get("nombre"));
        if (datos.containsKey("apellido"))
            director.setApellido((String) datos.get("apellido"));
        if (datos.containsKey("email"))
            director.setEmail((String) datos.get("email"));
        if (datos.containsKey("contrasenna"))
            director.setContrasenna((String) datos.get("contrasenna"));

        return (AUsuario) repositorioDirector.guardar(director);
    }
}

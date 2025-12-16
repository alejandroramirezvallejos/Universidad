package com.example.Server.estrategias.usuario;

import com.example.Server.modelos.DirectorCarrera;
import com.example.Server.modelos.Usuario;
import com.example.Server.repositorios.RepositorioDirectorCarrera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class UsuarioDirector implements IEstrategiaUsuario {
    @Autowired
    private RepositorioDirectorCarrera repositorioDirector;

    @Override
    public Usuario buscar(String codigo) {
        return repositorioDirector.buscarPorCodigo(codigo);
    }

    @Override
    public Usuario actualizar(String codigo, Map<String, Object> datos) {
        DirectorCarrera director = repositorioDirector.buscarPorCodigo(codigo);

        if (director == null)
            return null;

        if (datos.containsKey("nombre")) director.setNombre((String) datos.get("nombre"));
        if (datos.containsKey("apellido")) director.setApellido((String) datos.get("apellido"));
        if (datos.containsKey("email")) director.setEmail((String) datos.get("email"));
        if (datos.containsKey("contrasenna")) director.setContrasenna((String) datos.get("contrasenna"));

        return repositorioDirector.guardar(director);
    }
}

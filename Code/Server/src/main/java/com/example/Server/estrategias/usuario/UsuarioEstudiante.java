package com.example.Server.estrategias.usuario;

import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Usuario;
import com.example.Server.repositorios.RepositorioEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class UsuarioEstudiante implements IEstrategiaUsuario {
    @Autowired
    private RepositorioEstudiante repositorioEstudiante;

    @Override
    public Usuario buscar(String codigo) {
        return repositorioEstudiante.buscarPorCodigo(codigo);
    }

    @Override
    public Usuario actualizar(String codigo, Map<String, Object> datos) {
        Estudiante estudiante = repositorioEstudiante.buscarPorCodigo(codigo);

        if (estudiante == null)
            return null;

        if (datos.containsKey("nombre")) estudiante.setNombre((String) datos.get("nombre"));
        if (datos.containsKey("apellido")) estudiante.setApellido((String) datos.get("apellido"));
        if (datos.containsKey("email")) estudiante.setEmail((String) datos.get("email"));
        if (datos.containsKey("contrasenna")) estudiante.setContrasenna((String) datos.get("contrasenna"));
        if (datos.containsKey("semestre")) estudiante.setSemestre((Integer) datos.get("semestre"));

        return repositorioEstudiante.guardar(estudiante);
    }
}

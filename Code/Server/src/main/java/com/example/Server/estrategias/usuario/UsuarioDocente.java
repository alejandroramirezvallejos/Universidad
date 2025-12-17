package com.example.Server.estrategias.usuario;
import com.example.Server.modelos.abstracciones.AUsuario;
import com.example.Server.modelos.abstracciones.IDocente;
import com.example.Server.repositorios.abstracciones.IRepositorioDocente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class UsuarioDocente implements IEstrategiaUsuario {
    @Autowired
    private IRepositorioDocente repositorioDocente;

    @Override
    public AUsuario buscar(String codigo) {
        return (AUsuario) repositorioDocente.buscarPorCodigo(codigo);
    }

    @Override
    public AUsuario actualizar(String codigo, Map<String, Object> datos) {
        IDocente docente = repositorioDocente.buscarPorCodigo(codigo);

        if (docente == null)
            return null;

        if (datos.containsKey("nombre")) docente.setNombre((String) datos.get("nombre"));
        if (datos.containsKey("apellido")) docente.setApellido((String) datos.get("apellido"));
        if (datos.containsKey("email")) docente.setEmail((String) datos.get("email"));
        if (datos.containsKey("contrasenna")) docente.setContrasenna((String) datos.get("contrasenna"));
        if (datos.containsKey("departamento")) docente.setDepartamento((String) datos.get("departamento"));
        if (datos.containsKey("especialidad")) docente.setEspecialidad((String) datos.get("especialidad"));

        return (AUsuario) repositorioDocente.guardar(docente);
    }
}

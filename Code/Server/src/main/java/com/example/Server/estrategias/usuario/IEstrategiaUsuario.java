package com.example.Server.estrategias.usuario;
import com.example.Server.modelos.Usuario;
import java.util.Map;

public interface IEstrategiaUsuario {
    Usuario buscar(String codigo);
    Usuario actualizar(String codigo, Map<String, Object> datos);
}

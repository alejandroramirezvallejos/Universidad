package com.example.Server.estrategias.usuario;
import com.example.Server.modelos.abstracciones.AUsuario;
import java.util.Map;

public interface IEstrategiaUsuario {
    AUsuario buscar(String codigo);
    AUsuario actualizar(String codigo, Map<String, Object> datos);
}

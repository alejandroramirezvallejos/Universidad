package com.example.Server.servicios.abstracciones;
import com.example.Server.modelos.abstracciones.AUsuario;
import java.util.Map;

public interface IServicioUsuario {
    AUsuario getUsuario(String codigo);
    AUsuario actualizar(String codigo, Map<String, Object> datos);
}


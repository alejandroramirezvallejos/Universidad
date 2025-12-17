package com.example.Server.servicios.abstracciones;

import java.util.Map;

public interface IServicioDashboard {
    Map<String, Object> generarDashboardEstudiante(String codigoEstudiante);
    Map<String, Object> generarDashboardDocente(String codigoDocente);
}


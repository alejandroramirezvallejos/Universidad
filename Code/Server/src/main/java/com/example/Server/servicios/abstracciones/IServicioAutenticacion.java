package com.example.Server.servicios.abstracciones;
import com.example.Server.modelos.abstracciones.AUsuario;
import com.example.Server.modelos.abstracciones.IDocente;
import com.example.Server.modelos.abstracciones.IEstudiante;

public interface IServicioAutenticacion {
    AUsuario login(AUsuario credenciales);
    IEstudiante registrarEstudiante(IEstudiante estudiante);
    IDocente registrarDocente(IDocente docente);
}


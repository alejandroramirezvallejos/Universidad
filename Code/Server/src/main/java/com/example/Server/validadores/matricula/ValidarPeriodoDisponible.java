package com.example.Server.validadores.matricula;

import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IGestion;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ValidarPeriodoDisponible implements IValidarMatricula {
    @Override
    public String validar(IEstudiante estudiante, IParaleloMateria paraleloMateria) {
        IGestion gestion = paraleloMateria.getGestion();

        if (gestion == null)
            return "El paralelo no tiene una gestión asignada";

        java.util.Date now = new java.util.Date();

        if (now.before(gestion.getFechaInicioMatricula()) || now.after(gestion.getFechaFinMatricula()))
             return "Fuera de periodo de inscripción";

        return null;
    }
}

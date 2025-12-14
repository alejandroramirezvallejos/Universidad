package com.example.Server.validadores;

import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Gestion;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * Validador que verifica que la matrícula se haga dentro del período permitido
 * La gestión debe estar en estado "EN_CURSO" y dentro de las fechas de matrícula
 */
@Component
public class ValidadorPeriodoMatricula implements IValidador {
    private IValidador siguiente;

    @Override
    public void setSiguiente(IValidador siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        Gestion gestion = paraleloMateria.getGestion();
        
        if (gestion == null) {
            return "El paralelo no tiene una gestión asignada";
        }

        // Verificar que la gestión esté activa
        if (!"EN_CURSO".equals(gestion.getEstado())) {
            return "La gestión no está activa para inscripciones. Estado: " + gestion.getEstado();
        }

        // Verificar fechas de matrícula
        Date hoy = new Date();
        Date fechaInicioMatricula = gestion.getFechaInicioMatricula();
        Date fechaFinMatricula = gestion.getFechaFinMatricula();

        if (fechaInicioMatricula != null && hoy.before(fechaInicioMatricula)) {
            return "El período de matrícula aún no ha iniciado. Inicia: " + fechaInicioMatricula;
        }

        if (fechaFinMatricula != null && hoy.after(fechaFinMatricula)) {
            return "El período de matrícula ha finalizado. Finalizó: " + fechaFinMatricula;
        }

        if (siguiente != null) {
            return siguiente.validar(estudiante, paraleloMateria);
        }

        return null;
    }
}

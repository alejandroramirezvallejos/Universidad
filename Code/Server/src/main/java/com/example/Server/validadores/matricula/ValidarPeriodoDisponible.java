package com.example.Server.validadores.matricula;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.ParaleloMateria;
import com.example.Server.modelos.Gestion;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ValidarPeriodoDisponible implements IValidarMatricula {
    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        Gestion gestion = paraleloMateria.getGestion();

        if (gestion == null)
            return "El paralelo no tiene una gestión asignada";

        return gestion.verificarPeriodoInscripcion();
    }
}

package com.example.Server.validadores;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.ParaleloMateria;
import com.example.Server.modelos.Gestion;
import org.springframework.stereotype.Component;

@Component
public class ValidarPeriodoMatricula extends Validar {
    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        Gestion gestion = paraleloMateria.getGestion();

        if (gestion == null)
            return "El paralelo no tiene una gestión asignada";

        String error = gestion.verificarPeriodoInscripcion();
        if (error != null)
            return error;

        return super.validar(estudiante, paraleloMateria);
    }
}

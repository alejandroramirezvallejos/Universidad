package com.example.Server.validadores;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.stereotype.Component;

@Component
public class ValidarMateriaAprobada extends Validar {
    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        Materia materia = paraleloMateria.getMateria();

        if (estudiante.haAprobado(materia))
            return "El estudiante ya aprobó la materia: " + materia.getNombre();

        return super.validar(estudiante, paraleloMateria);
    }
}

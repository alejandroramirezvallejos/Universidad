package com.example.Server.validadores;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.stereotype.Component;

@Component
public class ValidarMateriaNoInscrita extends Validar {
    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateriaNuevo) {
        Materia materiaNueva = paraleloMateriaNuevo.getMateria();

        if (estudiante.estaInscritoEnMateria(materiaNueva))
            return "El estudiante ya esta inscrito en un paralelo de esta materia";

        return super.validar(estudiante, paraleloMateriaNuevo);
    }
}

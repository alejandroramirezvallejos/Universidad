package com.example.Server.validadores.matricula;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class ValidarMateriaNoInscrita implements IValidarMatricula {
    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateriaNuevo) {
        Materia materiaNueva = paraleloMateriaNuevo.getMateria();

        if (estudiante.getMateriasInscritas() != null && estudiante.getMateriasInscritas().contains(materiaNueva))
            return "El estudiante ya esta inscrito en un paralelo de esta materia";

        return null;
    }
}

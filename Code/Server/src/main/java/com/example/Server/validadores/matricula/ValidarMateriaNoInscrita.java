package com.example.Server.validadores.matricula;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IMateria;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class ValidarMateriaNoInscrita implements IValidarMatricula {
    @Override
    public String validar(IEstudiante estudiante, IParaleloMateria paraleloMateriaNuevo) {
        IMateria materiaNueva = paraleloMateriaNuevo.getMateria();

        if (estudiante.getMateriasInscritas() != null && estudiante.getMateriasInscritas().contains(materiaNueva))
            return "El estudiante ya esta inscrito en un paralelo de esta materia";

        return null;
    }
}

package com.example.Server.validadores;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ValidadorEstudiante {
    public boolean estaInscritoEnParalelo(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        List<Estudiante> estudiantesDelParalelo = paraleloMateria.getEstudiantes();

        for (Estudiante estudianteDelParalelo : estudiantesDelParalelo)
            if (estudianteDelParalelo.getCodigo().equals(estudiante.getCodigo()))
                return true;

        return false;
    }

    public boolean tieneInscritaMateria(Estudiante estudiante, String codigoMateria) {
        List<Materia> materiasInscritas = estudiante.getMateriasInscritas();

        for (Materia materiaInscrita : materiasInscritas)
            if (materiaInscrita.getCodigo().equals(codigoMateria))
                return true;

        return false;
    }
}


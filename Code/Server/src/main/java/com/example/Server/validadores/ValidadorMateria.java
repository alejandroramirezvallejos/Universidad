package com.example.Server.validadores;
import com.example.Server.modelos.Materia;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ValidadorMateria {
    public boolean existeEnLista(Materia materiaBuscada, List<Materia> materias) {
        for (Materia materia : materias)
            if (materia.getCodigo().equals(materiaBuscada.getCodigo()))
                return true;

        return false;
    }
}


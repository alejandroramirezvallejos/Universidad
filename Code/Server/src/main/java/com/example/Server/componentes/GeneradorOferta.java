package com.example.Server.componentes;

import com.example.Server.modelos.Gestion;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import com.example.Server.repositorios.RepositorioParaleloMateria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GeneradorOferta {
    private final RepositorioParaleloMateria repositorioParalelo;

    public Gestion generar(Gestion gestion) {
        List<ParaleloMateria> todosLosParalelos = repositorioParalelo.getParalelos();
        Map<String, Materia> materiasMap = new HashMap<>();

        for (ParaleloMateria paralelo : todosLosParalelos) {
            if (paralelo.getGestion() != null && paralelo.getGestion().getCodigo().equals(gestion.getCodigo())) {
                Materia materiaOriginal = paralelo.getMateria();
                Materia materiaResponse = materiasMap.get(materiaOriginal.getCodigo());
                if (materiaResponse == null) {
                    materiaResponse = copiarMateria(materiaOriginal);
                    materiasMap.put(materiaOriginal.getCodigo(), materiaResponse);
                }
                materiaResponse.getParaleloMaterias().add(paralelo);
            }
        }
        gestion.setMaterias(new ArrayList<>(materiasMap.values()));
        return gestion;
    }

    private Materia copiarMateria(Materia original) {
        Materia copia = new Materia();
        copia.setCodigo(original.getCodigo());
        copia.setNombre(original.getNombre());
        copia.setSemestre(original.getSemestre());
        copia.setCreditos(original.getCreditos());
        copia.setActiva(original.isActiva());
        copia.setCarrera(original.getCarrera());
        copia.setMateriasCorrelativas(original.getMateriasCorrelativas());
        copia.setParaleloMaterias(new ArrayList<>());
        return copia;
    }
}


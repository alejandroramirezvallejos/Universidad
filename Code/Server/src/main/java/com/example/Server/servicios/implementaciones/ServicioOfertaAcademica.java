package com.example.Server.servicios.implementaciones;

import com.example.Server.modelos.abstracciones.IGestion;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import com.example.Server.modelos.implementaciones.Gestion;
import com.example.Server.modelos.implementaciones.Materia;
import com.example.Server.repositorios.abstracciones.IRepositorioGestion;
import com.example.Server.repositorios.abstracciones.IRepositorioParaleloMateria;
import com.example.Server.servicios.abstracciones.IServicioOfertaAcademica;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ServicioOfertaAcademica implements IServicioOfertaAcademica {
    private final IRepositorioGestion repositorioGestion;
    private final IRepositorioParaleloMateria repositorioParalelo;

    @Override
    public IGestion getOfertaPorGestion(String codigoGestion) {
        IGestion gestion = repositorioGestion.buscarPorCodigo(codigoGestion);
        if (gestion == null)
            throw new RuntimeException("Gestión no encontrada");
        return generar((Gestion) gestion);
    }

    private Gestion generar(Gestion gestion) {
        List<IParaleloMateria> paralelos = repositorioParalelo.getParalelos();
        Map<String, Materia> materias = new HashMap<>();

        for (IParaleloMateria paralelo : paralelos) {
            if (paralelo.getGestion() != null && paralelo.getGestion().getCodigo().equals(gestion.getCodigo())) {
                Materia materiaOriginal = (Materia) paralelo.getMateria();
                Materia materiaResponse = materias.get(materiaOriginal.getCodigo());

                if (materiaResponse == null) {
                    materiaResponse = materiaOriginal.clonar();
                    materias.put(materiaOriginal.getCodigo(), materiaResponse);
                }

                materiaResponse.getParaleloMaterias().add(paralelo);
            }
        }

        gestion.setMaterias(new ArrayList<>(materias.values()));

        return gestion;
    }
}

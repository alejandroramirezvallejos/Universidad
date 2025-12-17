package com.example.Server.servicios;
import com.example.Server.modelos.Gestion;
import com.example.Server.modelos.IMateria;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import com.example.Server.repositorios.RepositorioGestion;
import com.example.Server.repositorios.RepositorioParaleloMateria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ServicioOfertaAcademica {
    private final RepositorioGestion repositorioGestion;
    private final RepositorioParaleloMateria repositorioParalelo;

    public Gestion getOfertaPorGestion(String codigoGestion) {
        Gestion gestion = repositorioGestion.buscarPorCodigo(codigoGestion)
                .orElseThrow(() -> new RuntimeException("Gestión no encontrada"));

        return generar(gestion);
    }

    private Gestion generar(Gestion gestion) {
        List<ParaleloMateria> paralelos = repositorioParalelo.getParalelos();
        Map<String, Materia> materias = new HashMap<>();

        for (ParaleloMateria paralelo : paralelos) {
            if (paralelo.getGestion() != null && paralelo.getGestion().getCodigo().equals(gestion.getCodigo())) {
                Materia materiaOriginal = paralelo.getMateria();
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

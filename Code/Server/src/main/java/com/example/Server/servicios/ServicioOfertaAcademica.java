package com.example.Server.servicios;
import com.example.Server.casts.CastOfertaAcademica;
import com.example.Server.dtos.*;
import com.example.Server.modelos.*;
import com.example.Server.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioOfertaAcademica {
    @Autowired
    private RepositorioGestion repositorioGestion;
    @Autowired
    private RepositorioMateria repositorioMateria;
    @Autowired
    private RepositorioParaleloMateria repositorioParalelo;
    @Autowired
    private RepositorioMatricula repositorioMatricula;
    @Autowired
    private CastOfertaAcademica convertidor;

    public DtoOfertaAcademica obtenerOfertaPorGestion(String codigoGestion) {
        Gestion gestion = repositorioGestion.buscarPorCodigo(codigoGestion).orElse(null);

        if (gestion == null)
            return null;

        List<Materia> todasLasMaterias = repositorioMateria.getMaterias();
        List<ParaleloMateria> todosLosParalelos = repositorioParalelo.getParalelos();

        return convertidor.getDto(gestion, todasLasMaterias, todosLosParalelos);
    }
}

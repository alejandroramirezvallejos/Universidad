package com.example.Server.servicios;
import com.example.Server.componentes.GeneradorOferta;
import com.example.Server.modelos.Gestion;
import com.example.Server.repositorios.RepositorioGestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicioOfertaAcademica {
    private final RepositorioGestion repositorioGestion;
    private final GeneradorOferta generador;

    public Gestion getOfertaPorGestion(String codigoGestion) {
        Gestion gestion = repositorioGestion.buscarPorCodigo(codigoGestion).orElse(null);

        if (gestion == null)
            return null;

        return generador.generar(gestion);
    }
}

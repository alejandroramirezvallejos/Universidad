package com.example.Server.casts;

import com.example.Server.dtos.DtoMateriaConParalelos;
import com.example.Server.dtos.DtoOfertaAcademica;
import com.example.Server.modelos.Gestion;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CastOfertaAcademica {

    @Autowired
    private CastMateriaOferta convertidorMateria;

    public DtoOfertaAcademica getDto(Gestion gestion, List<Materia> materias, List<ParaleloMateria> paralelos) {
        DtoOfertaAcademica oferta = new DtoOfertaAcademica();
        oferta.setCodigoGestion(gestion.getCodigo());
        oferta.setNombreGestion(gestion.getNombre());

        List<DtoMateriaConParalelos> materiasDto = new ArrayList<>();
        for (Materia materia : materias)
            materiasDto.add(convertidorMateria.getDto(materia, gestion, paralelos));

        oferta.setMaterias(materiasDto);
        return oferta;
    }
}

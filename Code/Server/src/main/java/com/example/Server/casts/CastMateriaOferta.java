package com.example.Server.casts;

import com.example.Server.dtos.DtoMateriaConParalelos;
import com.example.Server.dtos.DtoParaleloDetalle;
import com.example.Server.modelos.Gestion;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CastMateriaOferta {

    @Autowired
    private CastParaleloOferta convertidorParalelo;

    public DtoMateriaConParalelos getDto(Materia materia, Gestion gestion, List<ParaleloMateria> todosLosParalelos) {
        DtoMateriaConParalelos dto = new DtoMateriaConParalelos();
        dto.setCodigo(materia.getCodigo());
        dto.setNombre(materia.getNombre());
        dto.setCreditos(materia.getCreditos());
        dto.setSemestre(materia.getSemestre());

        List<DtoParaleloDetalle> paralelosDto = new ArrayList<>();
        for (ParaleloMateria p : todosLosParalelos)
            if (p.getMateria().getCodigo().equals(materia.getCodigo()) &&
                p.getGestion() != null &&
                p.getGestion().getCodigo().equals(gestion.getCodigo()))
                paralelosDto.add(convertidorParalelo.getDto(p));

        dto.setParalelos(paralelosDto);
        return dto;
    }
}

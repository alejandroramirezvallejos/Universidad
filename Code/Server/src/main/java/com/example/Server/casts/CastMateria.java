package com.example.Server.casts;

import com.example.Server.dtos.DtoMateria;
import com.example.Server.modelos.Materia;
import org.springframework.stereotype.Component;

@Component
public class CastMateria {

    public DtoMateria getDto(Materia materia) {
        DtoMateria dto = new DtoMateria();
        dto.setCodigo(materia.getCodigo());
        dto.setNombre(materia.getNombre());
        dto.setSemestre(materia.getSemestre());
        dto.setCreditos(materia.getCreditos());
        return dto;
    }

    public Materia getModelo(DtoMateria dto) {
        Materia materia = new Materia();
        materia.setCodigo(dto.getCodigo());
        materia.setNombre(dto.getNombre());
        materia.setSemestre(dto.getSemestre());
        materia.setCreditos(dto.getCreditos());
        return materia;
    }
}

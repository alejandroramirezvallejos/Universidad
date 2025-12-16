package com.example.Server.casts;

import com.example.Server.dtos.DtoDocente;
import com.example.Server.modelos.Docente;
import org.springframework.stereotype.Component;

@Component
public class CastDocente {

    public DtoDocente getDto(Docente docente) {
        DtoDocente dto = new DtoDocente();
        dto.setCodigo(docente.getCodigo());
        dto.setNombre(docente.getNombre());
        dto.setEspecialidad(docente.getEspecialidad());
        return dto;
    }

    public Docente getModelo(DtoDocente dto) {
        Docente docente = new Docente();
        docente.setCodigo(dto.getCodigo());
        docente.setNombre(dto.getNombre());
        docente.setEspecialidad(dto.getEspecialidad());
        return docente;
    }
}

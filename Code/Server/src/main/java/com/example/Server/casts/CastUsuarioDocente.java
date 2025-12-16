package com.example.Server.casts;

import com.example.Server.dtos.DtoUsuarioCompleto;
import com.example.Server.modelos.Docente;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CastUsuarioDocente {

    public DtoUsuarioCompleto getDto(Docente docente) {
        DtoUsuarioCompleto dto = new DtoUsuarioCompleto();
        BeanUtils.copyProperties(docente, dto);
        dto.setRol("DOCENTE");
        dto.setCodigoDocente(docente.getCodigo());
        return dto;
    }
}


package com.example.Server.casts;

import com.example.Server.dtos.DtoUsuarioCompleto;
import com.example.Server.modelos.DirectorCarrera;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CastUsuarioDirector {

    @Autowired
    private CastCarrera castCarrera;

    public DtoUsuarioCompleto getDto(DirectorCarrera director) {
        DtoUsuarioCompleto dto = new DtoUsuarioCompleto();
        BeanUtils.copyProperties(director, dto);
        dto.setRol("DIRECTOR");
        dto.setCodigoDirector(director.getCodigo());

        if (director.getCarrera() != null)
            dto.setCarrera(castCarrera.getDto(director.getCarrera()));

        return dto;
    }
}


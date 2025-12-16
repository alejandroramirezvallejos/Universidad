package com.example.Server.casts;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import com.example.Server.modelos.Estudiante;
import com.example.Server.dtos.DtoUsuarioCompleto;

@Component
public class CastUsuarioEstudiante {

    @Autowired
    private CastCarrera castCarrera;

    public DtoUsuarioCompleto getDto(Estudiante estudiante) {
        DtoUsuarioCompleto dto = new DtoUsuarioCompleto();
        BeanUtils.copyProperties(estudiante, dto);
        dto.setRol("ESTUDIANTE");
        dto.setCodigoEstudiante(estudiante.getCodigo());
        if (estudiante.getCarrera() != null) {
            dto.setCarrera(castCarrera.getDto(estudiante.getCarrera()));
        }
        return dto;
    }

}

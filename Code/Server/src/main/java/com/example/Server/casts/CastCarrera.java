package com.example.Server.casts;

import com.example.Server.dtos.DtoCarrera;
import com.example.Server.modelos.Carrera;
import org.springframework.stereotype.Component;

@Component
public class CastCarrera {

    public DtoCarrera getDto(Carrera carrera) {
        DtoCarrera dto = new DtoCarrera();
        dto.setCodigo(carrera.getCodigo());
        dto.setNombre(carrera.getNombre());
        return dto;
    }

    public Carrera getModelo(DtoCarrera dto) {
        Carrera carrera = new Carrera();
        carrera.setCodigo(dto.getCodigo());
        carrera.setNombre(dto.getNombre());
        return carrera;
    }
}

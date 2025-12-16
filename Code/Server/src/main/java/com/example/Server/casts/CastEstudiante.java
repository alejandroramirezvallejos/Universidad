package com.example.Server.casts;

import com.example.Server.dtos.DtoEstudiante;
import com.example.Server.modelos.Estudiante;
import org.springframework.stereotype.Component;

@Component
public class CastEstudiante {

    public DtoEstudiante getDto(Estudiante estudiante) {
        DtoEstudiante dto = new DtoEstudiante();
        dto.setCodigo(estudiante.getCodigo());
        dto.setNombre(estudiante.getNombre());
        dto.setEmail(estudiante.getEmail());
        return dto;
    }

    public Estudiante getModelo(DtoEstudiante dto) {
        Estudiante estudiante = new Estudiante();
        estudiante.setCodigo(dto.getCodigo());
        estudiante.setNombre(dto.getNombre());
        estudiante.setEmail(dto.getEmail());
        return estudiante;
    }
}

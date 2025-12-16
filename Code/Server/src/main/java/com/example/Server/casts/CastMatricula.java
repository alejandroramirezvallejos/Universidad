package com.example.Server.casts;

import com.example.Server.dtos.DtoMatricula;
import com.example.Server.modelos.Matricula;
import org.springframework.stereotype.Component;

@Component
public class CastMatricula {

    public DtoMatricula getDto(Matricula matricula) {
        DtoMatricula dto = new DtoMatricula();
        dto.setEstado(matricula.getEstado());
        dto.setParaleloMateria(matricula.getParaleloMateria());
        dto.setEstudiante(matricula.getEstudiante());
        return dto;
    }

    public Matricula getModelo(DtoMatricula dto) {
        Matricula matricula = new Matricula();
        matricula.setEstado(dto.getEstado());
        matricula.setParaleloMateria(dto.getParaleloMateria());
        matricula.setEstudiante(dto.getEstudiante());
        return matricula;
    }
}

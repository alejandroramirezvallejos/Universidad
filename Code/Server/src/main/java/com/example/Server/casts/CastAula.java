package com.example.Server.casts;

import com.example.Server.dtos.DtoAula;
import com.example.Server.modelos.Aula;
import org.springframework.stereotype.Component;

@Component
public class CastAula {

    public DtoAula getDto(Aula aula) {
        DtoAula dto = new DtoAula();
        dto.setCodigo(aula.getCodigo());
        dto.setEdificio(aula.getEdificio());
        dto.setCapacidad(aula.getCapacidad());
        dto.setDisponible(aula.isDisponible());
        return dto;
    }

    public Aula getModelo(DtoAula dto) {
        Aula aula = new Aula();
        aula.setCodigo(dto.getCodigo());
        aula.setEdificio(dto.getEdificio());
        aula.setCapacidad(dto.getCapacidad());
        aula.setDisponible(dto.isDisponible());
        return aula;
    }
}

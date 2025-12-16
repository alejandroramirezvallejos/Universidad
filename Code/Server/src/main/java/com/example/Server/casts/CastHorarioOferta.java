package com.example.Server.casts;

import org.springframework.stereotype.Component;
import com.example.Server.modelos.Horario;
import com.example.Server.modelos.Aula;
import com.example.Server.dtos.DtoHorario;

@Component
public class CastHorarioOferta {

    public DtoHorario getDto(Horario horario, Aula aula) {
        DtoHorario dto = new DtoHorario();
        dto.setDiaSemana(horario.getDiaSemana());
        dto.setHoraInicio(horario.getHoraInicio().toString());
        dto.setHoraFin(horario.getHoraFin().toString());
        return dto;
    }

}

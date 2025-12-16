package com.example.Server.casts;

import com.example.Server.dtos.DtoHorario;
import com.example.Server.dtos.DtoParaleloDetalle;
import com.example.Server.modelos.Horario;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CastParaleloOferta {

    @Autowired
    private CastHorarioOferta convertidorHorario;

    public DtoParaleloDetalle getDto(ParaleloMateria paralelo) {
        DtoParaleloDetalle dto = new DtoParaleloDetalle();
        dto.setId(Long.valueOf(paralelo.getCodigo().hashCode()));
        dto.setNumeroParalelo(paralelo.getCodigo());
        dto.setCupoTotal(paralelo.getCupoMaximo() != null ? paralelo.getCupoMaximo() : 30);

        int inscritos = paralelo.getEstudiantes() != null ? paralelo.getEstudiantes().size() : 0;
        dto.setCupoDisponible(dto.getCupoTotal() - inscritos);

        if (paralelo.getDocente() != null) {
            dto.setNombreDocente(paralelo.getDocente().getNombre() + " " + paralelo.getDocente().getApellido());
            dto.setCodigoDocente(paralelo.getDocente().getCodigo());
        }

        List<DtoHorario> horariosDto = new ArrayList<>();
        for (Horario h : paralelo.getHorarios())
            horariosDto.add(convertidorHorario.getDto(h, paralelo.getAula()));

        dto.setHorarios(horariosDto);
        return dto;
    }
}

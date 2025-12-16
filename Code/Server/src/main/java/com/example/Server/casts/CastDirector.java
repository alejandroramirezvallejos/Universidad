package com.example.Server.casts;

import com.example.Server.dtos.DtoDirectorCarrera;
import com.example.Server.modelos.DirectorCarrera;
import org.springframework.stereotype.Component;

@Component
public class CastDirector {

    public DtoDirectorCarrera getDto(DirectorCarrera director) {
        DtoDirectorCarrera dto = new DtoDirectorCarrera();
        dto.setCodigo(director.getCodigo());
        dto.setNombre(director.getNombre() + " " + director.getApellido());
        dto.setEmail(director.getEmail());
        return dto;
    }

    public DirectorCarrera getModelo(DtoDirectorCarrera dto) {
        DirectorCarrera director = new DirectorCarrera();
        director.setCodigo(dto.getCodigo());
        String[] partes = dto.getNombre().split(" ", 2);
        director.setNombre(partes.length > 0 ? partes[0] : dto.getNombre());
        director.setApellido(partes.length > 1 ? partes[1] : "");
        director.setEmail(dto.getEmail());
        return director;
    }
}

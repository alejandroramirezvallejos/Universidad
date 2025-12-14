package com.example.Server.dtos;

import lombok.Data;
import java.util.List;

@Data
public class DtoInscripcionBatch {
    private List<DtoInscripcion> inscripciones;
}

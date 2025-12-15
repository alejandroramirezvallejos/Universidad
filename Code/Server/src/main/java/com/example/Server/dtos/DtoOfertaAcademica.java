package com.example.Server.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoOfertaAcademica {
    private String codigoGestion;
    private String nombreGestion;
    private List<DtoMateriaConParalelos> materias;
}

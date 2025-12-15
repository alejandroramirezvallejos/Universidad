package com.example.Server.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoParaleloDetalle {
    private Long id;
    private String numeroParalelo;
    private int cupoTotal;
    private int cupoDisponible;
    private String nombreDocente;
    private String codigoDocente;
    private List<DtoHorario> horarios;
}

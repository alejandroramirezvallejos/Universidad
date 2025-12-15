package com.example.Server.dtos;
import lombok.Data;
import java.util.List;

@Data
public class DtoDashboardDocente {
    private String codigoDocente;
    private String nombreCompleto;
    private String departamento;
    private String especialidad;
    private int paralelosActivos;
    private int totalEstudiantes;
    private int materiasImpartidas;
    private List<DtoParaleloDocente> paralelosActuales;
    private List<DtoEstadisticaMateria> estadisticasMaterias;
}

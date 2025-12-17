package com.example.Server.servicios.abstracciones;
import com.example.Server.modelos.abstracciones.ICalificacion;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IHistorialAcademico;
import java.util.List;

public interface IServicioHistorialAcademico {
    IHistorialAcademico crear(IEstudiante estudiante);
    List<IHistorialAcademico> getHistoriales();
    void eliminar(IHistorialAcademico historial);
    IHistorialAcademico getHistorialPorEstudiante(String estudianteCodigo);
    Double calcularPromedioGeneral(String estudianteCodigo);
    ICalificacion getPromedioEstudiante(String estudianteCodigo);
}


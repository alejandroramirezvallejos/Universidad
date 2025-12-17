package com.example.Server.servicios.abstracciones;

import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IMatricula;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import java.util.List;

public interface IServicioInscripcion {
    IMatricula crear(IEstudiante estudiante, IParaleloMateria paralelo);
    void aceptar(IMatricula matricula);
    List<IMatricula> getMatriculas();
    List<IMatricula> inscribirBatch(List<IMatricula> inscripciones);
    List<IMatricula> getMatriculasPorEstudiante(String estudianteCodigo);
    List<IMatricula> getMatriculasPorParalelo(String paraleloCodigo);
    void cancelar(String estudianteCodigo, String paraleloCodigo);
}


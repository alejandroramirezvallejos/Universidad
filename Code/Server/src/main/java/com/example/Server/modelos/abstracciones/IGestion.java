package com.example.Server.modelos.abstracciones;
import java.util.Date;
import java.util.List;

public interface IGestion {
    String getCodigo();
    void setCodigo(String codigo);
    String getNombre();
    void setNombre(String nombre);
    Integer getAnio();
    void setAnio(Integer anio);
    Integer getPeriodo();
    void setPeriodo(Integer periodo);
    Date getFechaInicio();
    void setFechaInicio(Date fechaInicio);
    Date getFechaFin();
    void setFechaFin(Date fechaFin);
    Date getFechaInicioMatricula();
    void setFechaInicioMatricula(Date fechaInicioMatricula);
    Date getFechaFinMatricula();
    void setFechaFinMatricula(Date fechaFinMatricula);
    String getEstado();
    void setEstado(String estado);
    List<IMateria> getMaterias();
    void setMaterias(List<IMateria> materias);
}

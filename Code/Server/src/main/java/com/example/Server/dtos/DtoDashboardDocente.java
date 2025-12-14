package com.example.Server.dtos;

import java.util.List;

/**
 * DTO para estadísticas del dashboard de docente
 */
public class DtoDashboardDocente {
    private String codigoDocente;
    private String nombreCompleto;
    private String departamento;
    private String especialidad;
    
    // Estadísticas de enseñanza
    private int paralelosActivos;
    private int totalEstudiantes;
    private int materiasImpartidas;
    
    // Listas detalladas
    private List<DtoParaleloDocente> paralelosActuales;
    private List<DtoEstadisticaMateria> estadisticasMaterias;

    // Constructors
    public DtoDashboardDocente() {
    }

    // Getters y Setters
    public String getCodigoDocente() {
        return codigoDocente;
    }

    public void setCodigoDocente(String codigoDocente) {
        this.codigoDocente = codigoDocente;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getParalelosActivos() {
        return paralelosActivos;
    }

    public void setParalelosActivos(int paralelosActivos) {
        this.paralelosActivos = paralelosActivos;
    }

    public int getTotalEstudiantes() {
        return totalEstudiantes;
    }

    public void setTotalEstudiantes(int totalEstudiantes) {
        this.totalEstudiantes = totalEstudiantes;
    }

    public int getMateriasImpartidas() {
        return materiasImpartidas;
    }

    public void setMateriasImpartidas(int materiasImpartidas) {
        this.materiasImpartidas = materiasImpartidas;
    }

    public List<DtoParaleloDocente> getParalelosActuales() {
        return paralelosActuales;
    }

    public void setParalelosActuales(List<DtoParaleloDocente> paralelosActuales) {
        this.paralelosActuales = paralelosActuales;
    }

    public List<DtoEstadisticaMateria> getEstadisticasMaterias() {
        return estadisticasMaterias;
    }

    public void setEstadisticasMaterias(List<DtoEstadisticaMateria> estadisticasMaterias) {
        this.estadisticasMaterias = estadisticasMaterias;
    }
}

// DTOs auxiliares
class DtoMateriaResumen {
    private String codigo;
    private String nombre;
    private String paralelo;
    private int creditos;
    
    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getParalelo() { return paralelo; }
    public void setParalelo(String paralelo) { this.paralelo = paralelo; }
    public int getCreditos() { return creditos; }
    public void setCreditos(int creditos) { this.creditos = creditos; }
}

class DtoCalificacionReciente {
    private String materia;
    private String evaluacion;
    private double nota;
    private String fecha;
    
    // Getters y Setters
    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }
    public String getEvaluacion() { return evaluacion; }
    public void setEvaluacion(String evaluacion) { this.evaluacion = evaluacion; }
    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}

class DtoParaleloDocente {
    private String codigoMateria;
    private String nombreMateria;
    private String paralelo;
    private int estudiantesInscritos;
    private int cupoMaximo;
    
    // Getters y Setters
    public String getCodigoMateria() { return codigoMateria; }
    public void setCodigoMateria(String codigoMateria) { this.codigoMateria = codigoMateria; }
    public String getNombreMateria() { return nombreMateria; }
    public void setNombreMateria(String nombreMateria) { this.nombreMateria = nombreMateria; }
    public String getParalelo() { return paralelo; }
    public void setParalelo(String paralelo) { this.paralelo = paralelo; }
    public int getEstudiantesInscritos() { return estudiantesInscritos; }
    public void setEstudiantesInscritos(int estudiantesInscritos) { this.estudiantesInscritos = estudiantesInscritos; }
    public int getCupoMaximo() { return cupoMaximo; }
    public void setCupoMaximo(int cupoMaximo) { this.cupoMaximo = cupoMaximo; }
}

class DtoEstadisticaMateria {
    private String materia;
    private double promedioGeneral;
    private int aprobados;
    private int reprobados;
    
    // Getters y Setters
    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }
    public double getPromedioGeneral() { return promedioGeneral; }
    public void setPromedioGeneral(double promedioGeneral) { this.promedioGeneral = promedioGeneral; }
    public int getAprobados() { return aprobados; }
    public void setAprobados(int aprobados) { this.aprobados = aprobados; }
    public int getReprobados() { return reprobados; }
    public void setReprobados(int reprobados) { this.reprobados = reprobados; }
}

package com.example.Server.dtos;

/**
 * DTO para usuario completo con información adicional según el rol
 */
public class DtoUsuarioCompleto {
    private String codigo;
    private String nombre;
    private String apellido;
    private String email;
    private String rol; // ESTUDIANTE, DOCENTE, DIRECTOR
    
    // Datos específicos de estudiante
    private String codigoEstudiante;
    private DtoCarrera carrera;
    private Integer semestre;
    
    // Datos específicos de docente
    private String codigoDocente;
    private String departamento;
    private String especialidad;
    
    // Datos específicos de director
    private String codigoDirector;

    // Constructores
    public DtoUsuarioCompleto() {}

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public DtoCarrera getCarrera() {
        return carrera;
    }

    public void setCarrera(DtoCarrera carrera) {
        this.carrera = carrera;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public String getCodigoDocente() {
        return codigoDocente;
    }

    public void setCodigoDocente(String codigoDocente) {
        this.codigoDocente = codigoDocente;
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

    public String getCodigoDirector() {
        return codigoDirector;
    }

    public void setCodigoDirector(String codigoDirector) {
        this.codigoDirector = codigoDirector;
    }
}

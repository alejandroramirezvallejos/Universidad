package com.example.Server.dtos;

/**
 * DTO para actualizar datos del usuario
 */
public class DtoActualizarUsuario {
    private String nombre;
    private String apellido;
    private String email;
    private String password; // Opcional - solo si quiere cambiar contraseña
    
    // Campos específicos de estudiante
    private Integer semestre;
    
    // Campos específicos de docente/director
    private String departamento;
    private String especialidad;

    // Constructors
    public DtoActualizarUsuario() {
    }

    // Getters y Setters
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
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
}

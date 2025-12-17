package com.example.Server.modelos.abstracciones;

public interface IUsuario {
    String getCodigo();
    void setCodigo(String codigo);
    String getNombre();
    void setNombre(String nombre);
    String getApellido();
    void setApellido(String apellido);
    String getEmail();
    void setEmail(String email);
    String getContrasenna();
    void setContrasenna(String contrasenna);
    String getRol();
    void setRol(String rol);
}


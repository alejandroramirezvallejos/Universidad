package com.example.Server.modelos;

public interface Usuario {
    String getCodigo();
    String getNombre();
    String getApellido();
    String getEmail();
    String getContrasenna();
    void setNombre(String nombre);
    void setApellido(String apellido);
    void setEmail(String email);
    void setContrasenna(String contrasenna);
}


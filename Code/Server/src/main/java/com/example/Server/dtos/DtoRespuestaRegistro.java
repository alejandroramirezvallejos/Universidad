package com.example.Server.dtos;

/**
 * DTO para respuesta de registro
 */
public class DtoRespuestaRegistro {
    private boolean exito;
    private String mensaje;
    private DtoUsuarioCompleto usuario;

    // Constructores
    public DtoRespuestaRegistro() {}

    public DtoRespuestaRegistro(boolean exito, String mensaje, DtoUsuarioCompleto usuario) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.usuario = usuario;
    }

    // Getters y Setters
    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public DtoUsuarioCompleto getUsuario() {
        return usuario;
    }

    public void setUsuario(DtoUsuarioCompleto usuario) {
        this.usuario = usuario;
    }
}

package com.example.Server.dtos;

/**
 * DTO para credenciales de login
 */
public class DtoCredenciales {
    private String email;
    private String password;

    // Constructores
    public DtoCredenciales() {}

    public DtoCredenciales(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters y Setters
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
}

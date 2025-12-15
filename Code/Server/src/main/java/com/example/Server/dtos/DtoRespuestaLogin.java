package com.example.Server.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoRespuestaLogin {
    private boolean exito;
    private String mensaje;
    private DtoUsuarioCompleto usuario;
}

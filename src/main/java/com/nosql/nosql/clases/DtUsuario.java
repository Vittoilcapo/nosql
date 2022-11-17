package com.nosql.nosql.clases;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DtUsuario {
    private String nombre;
    private String apellido;
    private String correo;
    private String imagen;
    private UUID idSesion;
}

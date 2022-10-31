package com.nosql.nosql.clases;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Usuario implements Serializable {
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private String imagen;
    private UUID idSesion= null;
}

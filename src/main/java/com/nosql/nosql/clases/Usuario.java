package com.nosql.nosql.clases;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Usuario implements Serializable {
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String correo;
    @NotBlank
    private String contrasena;
    private String imagen;
    private UUID idSesion= null;
    private String idCarrito;
}

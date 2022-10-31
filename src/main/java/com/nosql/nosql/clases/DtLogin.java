package com.nosql.nosql.clases;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class DtLogin {
    private String correo;
    private String contrasena;
}

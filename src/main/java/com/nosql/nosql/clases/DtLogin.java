package com.nosql.nosql.clases;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@ToString
public class DtLogin {
    @NotBlank
    private String correo;
    @NotBlank
    private String contrasena;
}

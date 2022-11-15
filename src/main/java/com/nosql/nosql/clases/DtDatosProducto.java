package com.nosql.nosql.clases;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DtDatosProducto implements Serializable {
    private String id;
    private Integer cantidad;
    private String nombre;
}

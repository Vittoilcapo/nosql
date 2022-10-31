package com.nosql.nosql.clases;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Producto implements Serializable {
    private String id;
    private String nombre;
    private Integer stock;
    private Double precio;
    private boolean permiteEnvio;
}

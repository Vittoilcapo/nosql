package com.nosql.nosql.clases;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Carrito implements Serializable {
    private String id;
    private List<DtDatosProducto> productos = new ArrayList<DtDatosProducto>();
}

package com.nosql.nosql.clases;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Getter
@NoArgsConstructor
@ToString
public class DtDatosProducto {
    private String id;
    private Integer cantidad;
}

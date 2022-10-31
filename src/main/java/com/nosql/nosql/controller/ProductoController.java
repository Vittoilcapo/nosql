package com.nosql.nosql.controller;

import com.nosql.nosql.clases.Producto;
import com.nosql.nosql.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    ProductoRepository productoRepository;


    @PostMapping()
    public void cargarProductos(){
        productoRepository.save(new Producto(UUID.randomUUID().toString(),"Televisor 32", 20, 200.00, false));
        productoRepository.save( new Producto(UUID.randomUUID().toString(),"Iphone 13", 5, 1500.00, true));
        productoRepository.save (new Producto(UUID.randomUUID().toString(),"RTX 4090", 0, 2000.00, true));
        productoRepository.save (new Producto(UUID.randomUUID().toString(),"Aire acondicionado", 50, 150.00, true));
    }
}

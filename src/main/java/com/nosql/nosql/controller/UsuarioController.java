package com.nosql.nosql.controller;

import com.nosql.nosql.clases.Carrito;
import com.nosql.nosql.clases.DtDatosProducto;
import com.nosql.nosql.clases.DtUsuario;
import com.nosql.nosql.clases.Usuario;
import com.nosql.nosql.clases.excepciones.Excepcion;
import com.nosql.nosql.repository.CarritoRepository;
import com.nosql.nosql.repository.UsuarioRepo;
import com.nosql.nosql.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioRepo usuarioRepo;

    @Autowired
    CarritoRepository carritoRepository;


    @GetMapping()
    public List<DtUsuario> findAll(){
        Map<String, Usuario> usuarios = usuarioRepo.findAll();
        List<DtUsuario> usuarioList = new ArrayList<>();
        for(Usuario usuario: usuarios.values()){
            usuarioList.add(new DtUsuario(usuario.getNombre(), usuario.getApellido(), usuario.getCorreo(), usuario.getImagen(), usuario.getIdSesion()));
        }
        return  usuarioList;
    }

    @PostMapping()
    public ResponseEntity<Object> crearUsuario(@Valid @RequestBody Usuario usuario) {
        if(usuarioRepo.existsById(usuario.getCorreo())){
            throw new Excepcion("Ya existe un usuario con ese correo");
        }
        Carrito carrito = new Carrito();
            carrito.setId(UUID.randomUUID().toString());
        carritoRepository.save(carrito);
        usuario.setIdCarrito(carrito.getId());
        usuarioRepo.save(usuario);
        return ResponseHandler.generateResponse("Usuario creado con exito", HttpStatus.OK, null);
    }

}

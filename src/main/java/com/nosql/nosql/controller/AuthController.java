package com.nosql.nosql.controller;

import com.nosql.nosql.clases.Carrito;
import com.nosql.nosql.clases.DtLogin;
import com.nosql.nosql.clases.Usuario;
import com.nosql.nosql.clases.excepciones.ExcepcionNotFound;
import com.nosql.nosql.repository.CarritoRepository;
import com.nosql.nosql.repository.SesionRepository;
import com.nosql.nosql.repository.UsuarioRepo;
import com.nosql.nosql.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UsuarioRepo usuarioRepo;

    @Autowired
    SesionRepository sesionRepository;

    @Autowired
    CarritoRepository carritoRepository;


    @PostMapping("/iniciarSesion")
    public ResponseEntity<Object> iniciarSesion(@Valid @RequestBody DtLogin datosLogin) {
        Usuario usuario = usuarioRepo.findById(datosLogin.getCorreo());
        if(usuario == null){
            throw new ExcepcionNotFound("El usuario no existe.");
        }
        if(!usuario.getContrasena().equals(datosLogin.getContrasena())){
            throw new ExcepcionNotFound("Contraseña incorrecta.");
        }
        Map<String, Object> datosSesion = new HashMap<>();
        boolean existeSesion= sesionRepository.existsById(usuario.getIdSesion());
        if(existeSesion) {
            datosSesion = sesionRepository.findAll(usuario.getIdSesion());
            datosSesion.put("idSesion", usuario.getIdSesion());
        }else {
            UUID idSesion = UUID.randomUUID();
            usuario.setIdSesion(idSesion);
            usuarioRepo.save(usuario);
            datosSesion.put("carrito", usuario.getIdCarrito());
            datosSesion.put("fechaCreada", new Date());
            datosSesion.put("correoUsuario", datosLogin.getCorreo());
            sesionRepository.save(idSesion, datosSesion);
            datosSesion.put("idSesion", idSesion);
        }
        String idcarrito = usuario.getIdCarrito();
        Carrito carrito = carritoRepository.findById(UUID.fromString(usuario.getIdCarrito()));
        datosSesion.put("carrito", carrito.getProductos());
        return ResponseHandler.generateResponse("Sesion iniciada", HttpStatus.OK, datosSesion);
    }
}

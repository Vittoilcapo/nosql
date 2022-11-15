package com.nosql.nosql.controller;

import com.nosql.nosql.clases.DtLogin;
import com.nosql.nosql.clases.Usuario;
import com.nosql.nosql.clases.excepciones.ExcepcionNotFound;
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

    @PostMapping("/iniciarSesion")
    public ResponseEntity<Object> iniciarSesion(@Valid @RequestBody DtLogin datosLogin) throws NoSuchFieldException, IllegalAccessException {
        Usuario usuario = usuarioRepo.findById(datosLogin.getCorreo());
        if(usuario == null){
            throw new ExcepcionNotFound("El usuario no existe.");
        }
        if(!usuario.getContrasena().equals(datosLogin.getContrasena())){
            throw new ExcepcionNotFound("Contraseña incorrecta.");
        }
        boolean existeSesion= sesionRepository.existsById(usuario.getIdSesion());
        if(existeSesion) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe una sesion con este usuario");
        }
        Map<String, Object> datosSesion = new HashMap<>();
        UUID idSesion = UUID.randomUUID();
        usuario.setIdSesion(idSesion);
        usuarioRepo.save(usuario);
        datosSesion.put("carrito", usuario.getIdCarrito());
        datosSesion.put("fechaCreada", new Date());
        datosSesion.put("correoUsuario", datosLogin.getCorreo());
        sesionRepository.create(idSesion, datosSesion);
        datosSesion.put("idSesion", idSesion);
        return ResponseHandler.generateResponse("Sesion iniciada", HttpStatus.OK, datosSesion);
    }
}

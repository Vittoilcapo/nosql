package com.nosql.nosql.controller;

import com.nosql.nosql.clases.DtDatosProducto;
import com.nosql.nosql.clases.Usuario;
import com.nosql.nosql.repository.UsuarioRepo;
import com.nosql.nosql.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioRepo usuarioRepo;


    @GetMapping()
    public Map<String, Usuario> findAll(){
        return usuarioRepo.findAll();
    }

    @PostMapping()
    public ResponseEntity<Object> crearUsuario(@RequestBody Usuario usuario) {
        usuarioRepo.save(usuario);
        return ResponseHandler.generateResponse("Usuario creado con exito", HttpStatus.OK, null);
    }

    @PostMapping("/imagen")
    public String crearUsuario2(@RequestParam("file") MultipartFile file) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] base = file.getBytes();
        //String base64 = Base64.getEncoder().encodeToString(base);
        return Base64.getEncoder().encodeToString(md.digest(base));
    }

}

package com.nosql.nosql.controller;

import com.nosql.nosql.clases.Usuario;
import com.nosql.nosql.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
public class UsuarioController {
    private UsuarioRepo usuarioRepo;

    public UsuarioController(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @GetMapping("/usuarios")
    public Map<String, Usuario> findAll(){
        return usuarioRepo.findAll();
    }

    @PostMapping("/usuarios")
    public void crearUsuario(@RequestBody Usuario usuario) throws IOException {
        usuarioRepo.save(usuario);
    }

    @PostMapping("/archivo")
    public String crearUsuario(@RequestParam("file") MultipartFile file) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] base = file.getBytes();
        //String base64 = Base64.getEncoder().encodeToString(base);
        return Base64.getEncoder().encodeToString(md.digest(base));
    }

}

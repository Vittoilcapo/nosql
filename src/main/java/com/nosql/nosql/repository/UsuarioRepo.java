package com.nosql.nosql.repository;

import com.nosql.nosql.clases.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class UsuarioRepo {
    private static final String KEY = "Usuarios";

    @Autowired
    RedisTemplate redisTemplate;
    private HashOperations hashOperations;

    @PostConstruct
    private void init() {
        hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(Usuario usuario) {
        hashOperations.put(KEY, usuario.getCorreo(), usuario);
    }

    public Map<String, Usuario> findAll(){
        return hashOperations.entries(KEY);
    }

    public Usuario findById(String correo) {
        return (Usuario) hashOperations.get(KEY,correo);
    }
    public Boolean existsById(String correo) {
        return  hashOperations.hasKey(KEY,correo);
    }
}

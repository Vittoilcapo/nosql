package com.nosql.nosql.repository;

import com.nosql.nosql.clases.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.UUID;

@Repository
public class UsuarioRepo{
    private static final String KEY = "Usuarios";
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @PostConstruct
    private void init() {
        hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(Usuario usuario) {
        redisTemplate.opsForValue().set(usuario.getCedula(), usuario.getDireccion().toString());
        hashOperations.put(KEY, UUID.randomUUID().toString(), usuario);
    }

    public Map<String, Usuario> findAll(){
        return hashOperations.entries(KEY);
    }
}

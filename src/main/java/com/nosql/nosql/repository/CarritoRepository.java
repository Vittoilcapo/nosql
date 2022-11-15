package com.nosql.nosql.repository;

import com.nosql.nosql.clases.Carrito;
import com.nosql.nosql.clases.Producto;
import com.nosql.nosql.clases.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.UUID;

@Repository
public class CarritoRepository {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private static final String KEY = "Carritos";

    private HashOperations hashOperations;

    @PostConstruct
    private void init() {
        hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(Carrito carrito) {
        hashOperations.put(KEY, carrito.getId(), carrito);
    }

    public Map<String, Carrito> findAll(){
        return hashOperations.entries(KEY);
    }

    public Carrito findById(UUID id) {
        return (Carrito) hashOperations.get(KEY,id.toString());
    }
}

package com.nosql.nosql.repository;

import com.nosql.nosql.clases.Producto;
import com.nosql.nosql.clases.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Repository
public class ProductoRepository {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private static final String KEY = "Productos";

    private HashOperations hashOperations;



    @PostConstruct
    private void init() {
        hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(Producto producto) {
      hashOperations.put(KEY,producto.getId(),producto);
    }

    public Map<String, Producto> findAll(){
        return hashOperations.entries(KEY);
    }


    public Usuario findById(UUID id) {
        return (Usuario) hashOperations.get(KEY,id.toString());
    }

}

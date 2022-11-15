package com.nosql.nosql.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Repository
public class SesionRepository {

    @Autowired
    RedisTemplate redisTemplate;

    private HashOperations hashOperations;

    private final String suffix="session:";


    @PostConstruct
    private void init() {
        hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(UUID id, Map<String, Object> datosSesion) {
        datosSesion.forEach((nombre, valor) -> hashOperations.put(suffix+id.toString(),nombre, valor));
        redisTemplate.expire(suffix+id.toString(),60, TimeUnit.HOURS);
    }

    public boolean existsById(UUID id){
       return redisTemplate.hasKey(suffix+id);
    }



    public Map<String, Object> findAll(UUID id){
        return hashOperations.entries(suffix+id.toString());
    }

}

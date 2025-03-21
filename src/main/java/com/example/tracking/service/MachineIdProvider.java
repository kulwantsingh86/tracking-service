package com.example.tracking.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MachineIdProvider {
    private final StringRedisTemplate redisTemplate;

    public MachineIdProvider(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public long getMachineId() {
        // Increment unique ID key in Redis (keeps track of active machines)
        return redisTemplate.opsForValue().increment("machine_id");
    }
}

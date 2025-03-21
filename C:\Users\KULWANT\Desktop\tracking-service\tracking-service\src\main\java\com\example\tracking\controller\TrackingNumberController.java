package com.example.tracking.controller;

import com.example.tracking.service.RedisSnowflakeIdGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tracking")
public class TrackingNumberController {
    private final RedisSnowflakeIdGenerator idGenerator;

    public TrackingNumberController(RedisSnowflakeIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @GetMapping("/generate")
    public String getTrackingNumber() {
        return "SN-" + idGenerator.generateId();
    }
}

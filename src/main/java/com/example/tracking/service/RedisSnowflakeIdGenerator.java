package com.example.tracking.service;

import org.springframework.stereotype.Service;

@Service
public class RedisSnowflakeIdGenerator {
    private static final long EPOCH = 1640995200000L;
    private static final long MACHINE_ID_BITS = 10;
    private static final long SEQUENCE_BITS = 12;
    
    private static final long MAX_SEQUENCE = (1L << SEQUENCE_BITS) - 1;

    private final long machineId;
    private long lastTimestamp = -1L;
    private long sequence = 0L;

    public RedisSnowflakeIdGenerator(MachineIdProvider machineIdProvider) {
        this.machineId = machineIdProvider.getMachineId();
    }

    public synchronized long generateId() {
        long currentTimestamp = System.currentTimeMillis();
        
        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                while ((currentTimestamp = System.currentTimeMillis()) <= lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        
        lastTimestamp = currentTimestamp;

        return ((currentTimestamp - EPOCH) << (MACHINE_ID_BITS + SEQUENCE_BITS))
                | (machineId << SEQUENCE_BITS)
                | sequence;
    }
}

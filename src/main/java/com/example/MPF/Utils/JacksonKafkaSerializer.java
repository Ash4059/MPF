package com.example.MPF.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JacksonKafkaSerializer<T> implements Serializer<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(JacksonKafkaSerializer.class);

    @Override
    public byte[] serialize(String s, T data) {
        logger.info("Serializing data: {}", data);
        try {
            return objectMapper.writeValueAsBytes(data);
        }catch (Exception e){
            logger.error("Error serializing JSON: {}", e.getMessage());
            throw new RuntimeException("Error serializing JSON " + e.getMessage());
        }
    }

}

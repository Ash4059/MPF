package com.example.MPF.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class JacksonKafkaDeserializer<T> implements Deserializer<T> {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(JacksonKafkaDeserializer.class);
    private Class<T> targetType;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey){
        String targetTypeName = (String) configs.get("target.type");
        logger.info("Configuring JacksonKafkaDeserializer with targetType: {}", targetTypeName);
        try {
            if(targetTypeName != null){
                this.targetType = (Class<T>) Class.forName(targetTypeName);
            }
        }catch (ClassNotFoundException e){
            logger.error("Failed to configure JacksonKafkaDeserializer", e);
            throw new RuntimeException("Failed to configure JacksonKafkaDeserializer", e);
        }
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        logger.info("Deserializing message from topic: " + topic + " targetType is " + this.targetType);
        try {
            return objectMapper.readValue(data, this.targetType);
        }catch (Exception e){
            logger.error("Failed to deserialized data", e);
            throw new RuntimeException("Failed to deserialized data", e);
        }
    }

}

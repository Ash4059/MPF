package com.example.MPF.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSenderService {

    private final KafkaTemplate<Integer, Object> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(KafkaSenderService.class);

    public KafkaSenderService(KafkaTemplate<Integer, Object> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void SendMessageToKafka(String topic, Object data){
        try{
            this.kafkaTemplate.send(topic, data);
            logger.info("Message sent to Kafka: {}", data);
        }catch (Exception e){
            logger.error("Error sending message to Kafka: {}", e.getMessage());
        }
    }

}

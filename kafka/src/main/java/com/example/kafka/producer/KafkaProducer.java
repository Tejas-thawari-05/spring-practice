package com.example.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/publish")
public class KafkaProducer {
    private final KafkaProducerS kafkaProducerS;

    
    public KafkaProducer(KafkaProducerS kafkaProducerS) {
        this.kafkaProducerS = kafkaProducerS;
    }

    @PostMapping("/{topic}")
    public String sendMessage(@PathVariable String topic, @RequestBody String message) {
        kafkaProducerS.sendMessage(topic, message);
        return "Message sent to topic: " + topic;
    }
}



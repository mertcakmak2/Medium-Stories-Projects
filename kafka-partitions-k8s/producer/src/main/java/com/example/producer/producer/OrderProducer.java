package com.example.producer.producer;


import com.example.producer.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {

    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public OrderProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(Order order) {
        this.kafkaTemplate.send("order-topic", order);
    }
}
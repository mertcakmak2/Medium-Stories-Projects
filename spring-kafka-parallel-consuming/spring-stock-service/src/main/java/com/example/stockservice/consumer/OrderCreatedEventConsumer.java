package com.example.stockservice.consumer;


import com.example.stockservice.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedEventConsumer {

    @KafkaListener(topics = "order-created-event", groupId = "stock-group", properties = {"spring.json.value.default.type=com.example.stockservice.model.Order"})
    public void consumeOrderCreatedEvent(Order order, Acknowledgment acknowledgment) {
        System.out.println(String.format("Stock Group, Order ID: %s", order.getOrderId()));
        acknowledgment.acknowledge();
    }

}

package com.example.paymentservice.consumer;


import com.example.paymentservice.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedEventConsumer {

    @KafkaListener(topics = "order-created-event", groupId = "payment-group", properties = {"spring.json.value.default.type=com.example.paymentservice.model.Order"})
    public void consumeOrderCreatedEvent(Order order, Acknowledgment acknowledgment) {
        System.out.println(String.format("Payment Group,  Order ID: %s", order.getOrderId()));
        acknowledgment.acknowledge();
    }

}

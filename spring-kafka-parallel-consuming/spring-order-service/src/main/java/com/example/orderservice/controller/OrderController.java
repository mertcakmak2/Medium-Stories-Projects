package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.producer.OrderCreatedEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    public final OrderCreatedEventProducer orderCreatedEventProducer;

    @Autowired
    public OrderController(OrderCreatedEventProducer orderCreatedEventProducer) {
        this.orderCreatedEventProducer = orderCreatedEventProducer;
    }

    @GetMapping(path = "")
    public String publishOrder() {
        var id = (int) (Math.random() * 100) + 1;
        var order = new Order(id, new Date());
        orderCreatedEventProducer.produce(order);

        return "Order creted";
    }
}

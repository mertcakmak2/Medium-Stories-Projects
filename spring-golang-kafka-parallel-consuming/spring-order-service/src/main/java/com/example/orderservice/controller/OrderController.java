package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.producer.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/order")
public class PublishOrderController {

    public final OrderProducer orderProducer;

    @Autowired
    public PublishOrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @GetMapping(path = "")
    public String publishOrder() {
        var order = new Order(1, new Date());
        orderProducer.produce(order);

        return "Orders published";
    }
}

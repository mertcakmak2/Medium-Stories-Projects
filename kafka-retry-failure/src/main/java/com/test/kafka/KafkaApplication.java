package com.test.kafka;

import com.test.kafka.model.Todo;
import com.test.kafka.producer.KafkaProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(KafkaProducer kafkaProducer) {
        return args -> {

            for (int i = 1; i < 10; i++) {
                var todo = Todo.builder()
                        .id(i)
                        .todo("todo description")
                        .build();
                kafkaProducer.sendTodoToKafka(todo);
            }

        };
    }

}

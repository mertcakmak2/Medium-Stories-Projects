package com.example.consumer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class TopicConfig {

    @Bean
    public NewTopic createTopic() {
        return TopicBuilder.name("order-topic")
                .partitions(9)
                .build();
    }

}

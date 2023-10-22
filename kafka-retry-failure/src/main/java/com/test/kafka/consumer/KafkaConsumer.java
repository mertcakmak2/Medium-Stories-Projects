package com.test.kafka.consumer;

import com.test.kafka.model.Todo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class KafkaConsumer {


    @KafkaListener(topics = "todo-topic", groupId = "group-id-1")
    public void todoTopicListener(@Payload Todo todo, Acknowledgment acknowledgment, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        log.info("Received a todo message key/id {}, date {}, partition {}", todo.getId(), new Date(), partition);

        // if throw exception while consuming data, retry policy works.
        if(todo.getId() % 6 == 0) {
            log.warn("throw exception");
            throw new IllegalStateException("consume topic exception");
        }
        acknowledgment.acknowledge();

    }

}

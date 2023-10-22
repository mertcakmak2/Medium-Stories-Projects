package com.test.kafka.producer;

import com.test.kafka.model.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTodoToKafka(Todo todo) {

        ListenableFuture<SendResult<String, Object>> listenableFuture = kafkaTemplate.send("todo-topic", String.valueOf(todo.getId()), todo);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                handleSuccess(todo.getId(), todo, result);
            }

            @Override
            public void onFailure(Throwable ex) {
                handleFailure(todo.getId(), todo, ex);
            }

        });
    }

    private void handleSuccess(Integer key, Todo value, SendResult<String, Object> result) {
        log.info("Success: Message sent success for the key: {} and the value is {} partition is {}"
                , key, value, result.getRecordMetadata().partition());

    }

    private void handleFailure(Integer key, Todo value, Throwable ex) {
        log.info("Error sending the message: exception is {}", ex.getMessage());
    }

}

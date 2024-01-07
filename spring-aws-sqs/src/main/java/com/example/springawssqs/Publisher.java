package com.example.springawssqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Log4j2
public class Publisher {

    @Value("${aws.queueName}")
    private String queueName;

    private final AmazonSQS amazonSQSClient;
    private final ObjectMapper objectMapper;

    public Publisher(AmazonSQS amazonSQSClient, ObjectMapper objectMapper) {
        this.amazonSQSClient = amazonSQSClient;
        this.objectMapper = objectMapper;
    }

    public void publishMessage() {
        try {
            GetQueueUrlResult queueUrl = amazonSQSClient.getQueueUrl(queueName);
            log.info("[PUBLISHER] Queue: {}", queueUrl.getQueueUrl());
            var message = Message.builder()
                    .id(UUID.randomUUID().toString())
                    .content("message")
                    .createdAt(new Date()).build();
            var result = amazonSQSClient.sendMessage(queueUrl.getQueueUrl(), objectMapper.writeValueAsString(message));
            log.info("[PUBLISHER] SQS Message ID: {}", result.getMessageId());
        } catch (Exception e) {
            log.error("Queue Exception Message: {}", e.getMessage());
        }

    }

}

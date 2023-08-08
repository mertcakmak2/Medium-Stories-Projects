package com.example.grpcclient.client;

import hello.HelloServiceGrpc;
import hello.Message;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelloGrpcClient {

    @Value("${hello.grpc.service.host}")
    private String helloGrpcServiceHost;

    @Value("${hello.grpc.service.port}")
    private int helloGrpcServicePort;

    private final static Logger log = LoggerFactory.getLogger(HelloGrpcClient.class);

    public String talkie() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(helloGrpcServiceHost, helloGrpcServicePort)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);
        Message message = Message.newBuilder().setContent("from spring boot client").build();
        var serverMessage = stub.talkie(message);
        log.info("Server message: {}",serverMessage.getContent());
        channel.shutdown();
        return serverMessage.getContent();
    }
}

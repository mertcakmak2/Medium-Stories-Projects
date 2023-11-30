package com.example.postservice.config;

import com.example.postservice.feign.MessageErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorDecoderBean {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new MessageErrorDecoder();
    }

}

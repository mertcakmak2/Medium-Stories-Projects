package com.example.springreactiveredis.config;

import com.example.springreactiveredis.domin.User;
import com.example.springreactiveredis.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Configuration
@Slf4j
public class ReactiveRedisConfig {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public ReactiveRedisTemplate<String, User> reactiveRedisTemplate(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        return new ReactiveRedisTemplate<String, User>(
                reactiveRedisConnectionFactory,
                RedisSerializationContext.fromSerializer(new Jackson2JsonRedisSerializer(User.class))
        );
    }

    @Bean
    public ReactiveRedisMessageListenerContainer redisMessageListenerContainer(UserRepository userRepository, ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        ReactiveRedisMessageListenerContainer container = new ReactiveRedisMessageListenerContainer(reactiveRedisConnectionFactory);
        ObjectMapper objectMapper = new ObjectMapper();
        container.receive(ChannelTopic.of("users"))
                .map(ReactiveSubscription.Message::getMessage)
                .map(m -> {
                    try {
                        return objectMapper.readValue(m, User.class);
                    } catch (IOException e) {
                        return null;
                    }
                })
                .switchIfEmpty(Mono.error(new IllegalArgumentException()))
                .flatMap(userRepository::saveUser)
                .subscribe(c-> log.info("User saved."));
        return container;
    }

}

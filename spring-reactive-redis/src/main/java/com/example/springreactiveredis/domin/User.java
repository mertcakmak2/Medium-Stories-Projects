package com.example.springreactiveredis.domin;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("users")
public class User {

    @Id
    private String id;
    private String name;
    private int age;

}

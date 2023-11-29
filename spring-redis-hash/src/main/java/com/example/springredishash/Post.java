package com.example.springredishash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "user", timeToLive = 100L)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private @Id String id;
    private @Indexed String firstName;
    private @Indexed String middleName;
    private @Indexed String lastName;
    private Role role;

    @TimeToLive
    private Long ttl;
}
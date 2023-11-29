package com.example.springredishash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private @Id String id;
    private @Indexed String roleName;

}

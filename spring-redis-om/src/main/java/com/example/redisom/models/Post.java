package com.example.redisom.models;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.TimeToLive;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//@Document(timeToLive = 300L)
@Document
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    private String id;

    @Indexed
    private String title;

    @Searchable
    private String content;

    @Indexed
    private int clapCount;

    @Indexed
    private Author author;

    @Indexed
    private Set<Comment> comments;

    @Indexed
    private Date createdDate;

    @TimeToLive
    private long ttl;
}

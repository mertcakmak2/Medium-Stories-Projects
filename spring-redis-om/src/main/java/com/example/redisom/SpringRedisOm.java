package com.example.redisom;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.example.redisom.config.MyKeyspaceConfiguration;
import com.example.redisom.models.*;
import com.example.redisom.repositories.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
@EnableRedisDocumentRepositories(basePackages = "com.example.redisom*")
public class SpringRedisOm {
    Logger logger = LoggerFactory.getLogger(SpringRedisOm.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringRedisOm.class, args);
    }

    @Bean
    CommandLineRunner runner(PostRepository repo) {
        return args -> {

            repo.deleteAll();

            var now = new Date();

            var author1 = Author.builder().name("mert").lastName("cakmak").build();
            var author2 = Author.builder().name("admin").lastName("admin").build();

            String content1 = "Redis OM Spring provides powerful repository and custom object-mapping abstractions built on top of the Spring Data Redis (SDR) framework.";
            var post1 = Post.builder().clapCount(50).title("What is Redis OM?").content(content1).comments(Set.of(Comment.builder().content("nice content").build())).createdDate(now).author(author1).ttl(100L).build();
            repo.save(post1);

            String content2 = "Java Spring Framework (Spring Framework) is a popular, open source, enterprise-level framework for creating standalone, production-grade applications that run on the Java Virtual Machine (JVM).";
            var post2 = Post.builder().clapCount(60).title("What is Spring Boot?").content(content2).comments(Set.of(Comment.builder().content("so cool").build(), Comment.builder().content("good post").build())).createdDate(now).author(author2).ttl(100L).build();
            repo.save(post2);

            logger.info("Save operations are successfully.");

            var postsByClapCount = repo.findByClapCountBetween(45, 55);
            logger.info("postsByClapCount: {}", postsByClapCount.toString());

            var postsByAuthor = repo.findByAuthor_NameAndAndAuthor_LastName(author2.getName(), author2.getLastName());
            logger.info("postsByAuthor: {}", postsByAuthor.toString());

            var postsSeachByContent = repo.searchByContent("open source");
            logger.info("postsSeachByContent: {}", postsSeachByContent.toString());

            var postsByCommentContent = repo.findByComments_Content("so cool");
            logger.info("postsByCommentContent: {}", postsByCommentContent.toString());

        };
    }

}

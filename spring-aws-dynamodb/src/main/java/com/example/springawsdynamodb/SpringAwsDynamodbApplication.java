package com.example.springawsdynamodb;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Log4j2
public class SpringAwsDynamodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAwsDynamodbApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(UserRepository userRepository){
        return args -> {
            log.info("Spring Boot Aws DynamoDB integration");

            // Save User
            var address = Address.builder().city("Test City").street("Test Street").build();
            var user = User.builder().address(address).username("user name").surname("user surname").age(25).build();
            var savedUser = userRepository.insertUser(user);

            var id = savedUser.getId();

            // Update the user
            user.setAge(18);
            userRepository.updateUser(id, user);

            // Find User By Id
            var existUser = userRepository.findUserById(id);
            log.info(String.format("Exist User Name: %s", existUser.getUsername()));

            log.info(id);

            userRepository.scanUserByBeginsWithUserName("edit");
            userRepository.scanUserByAgeLessThan(20);

            // Will delete saved user.
            // userRepository.deleteUserById(id, existUser.getUsername());
        };
    }

}

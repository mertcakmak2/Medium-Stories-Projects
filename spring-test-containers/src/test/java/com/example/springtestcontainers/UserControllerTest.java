package com.example.springtestcontainers;

import com.example.springtestcontainers.domain.User;
import com.example.springtestcontainers.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    @Container
    static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres:15")
            .withDatabaseName("test")
            .withUsername("root")
            .withPassword("root");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlContainer::getUsername);
        registry.add("spring.datasource.password", postgresqlContainer::getPassword);
    }

    @Autowired
    private UserRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    private String id;

    @BeforeAll
    static void beforeAll() {
        postgresqlContainer.start();

    }

    @AfterAll
    static void afterAll() {
        postgresqlContainer.stop();
    }

    @BeforeEach
    void setUp() {
//        repository.deleteAll();
//
//        var savedUser = repository.save(User.builder().name("mert").build());
//        this.id = savedUser.getId();
    }

    @Test
    @Order(1)
    public void should_save_user_and_return_created_status_code() {

        var user = User.builder().name("mert").build();

        ResponseEntity<User> createResponse = restTemplate.postForEntity("/api/v1/users", user, User.class);
        User savedUser = createResponse.getBody();

        // org.junit.jupiter.api.Assertions
        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        assertEquals(user.getName(), savedUser.getName());
        assertNotNull(savedUser);
    }

    @Test
    @Order(2)
    public void should_find_user_by_id_and_return_ok_status_code() {
        var user = User.builder().name("mert").build();
        repository.save(user);

        var url = String.format("/api/v1/users/%s", user.getId());

        ResponseEntity<User> findUserByIdResponse = restTemplate.getForEntity(url, User.class);
        User existUser = findUserByIdResponse.getBody();

        // org.junit.jupiter.api.Assertions
        assertEquals(HttpStatus.OK, findUserByIdResponse.getStatusCode());
        assertNotNull(existUser);

    }
}


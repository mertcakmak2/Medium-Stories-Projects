package com.example.springtestcontainers;

import com.example.springtestcontainers.domain.User;
import com.example.springtestcontainers.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.util.Assert;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // deactivate the default behaviour
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {

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
        repository.deleteAll();
    }

    @Test
    @Order(1)
    public void should_save_user_and_equal_given_name_and_saved_user_name() {
        User user = generateUser();
        var savedUser = repository.save(user);

        // org.assertj.core.api
        Assertions.assertThat(savedUser.getName()).isEqualTo(user.getName());
        Assert.notNull(savedUser.getId(), "Id should not be null!");
    }

    @Test
    @Order(2)
    public void should_get_user_by_name_and_equal_given_name_and_saved_user_name() {
        User user = generateUser();
        var savedUser = repository.save(user);

        var users = repository.getUserByName(savedUser.getName());

        // org.assertj.core.api
        Assertions.assertThat(users.size()).isEqualTo(1);
        Assertions.assertThat(users.get(0).getName()).isEqualTo(user.getName());
    }

    private User generateUser() {
        var name = "Mert";
        return User.builder().name(name).build();
    }
}

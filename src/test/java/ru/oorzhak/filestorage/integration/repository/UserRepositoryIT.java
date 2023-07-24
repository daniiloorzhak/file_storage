package ru.oorzhak.filestorage.integration.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.oorzhak.filestorage.models.Account;
import ru.oorzhak.filestorage.repository.UserRepository;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryIT {
    @Autowired
    private UserRepository userRepository;

    @Container
    private static final PostgreSQLContainer<?> CONTAINER
            = new PostgreSQLContainer<>("postgres:latest")
            .withExposedPorts(5432, 5432)
            .withDatabaseName("testdb")
            .withUsername("postgres")
            .withPassword("postgres");

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", CONTAINER::getUsername);
        registry.add("spring.datasource.password", CONTAINER::getPassword);
        registry.add("spring.datasource.driver-class-name", CONTAINER::getDriverClassName);
    }

    @Test
    public void exampleTest() {
        Assertions.assertEquals(1, userRepository.count());
        userRepository.save(
                Account.builder()
                .username("username")
                .password("password")
                .build());
        Assertions.assertEquals(2, userRepository.count());
    }
}

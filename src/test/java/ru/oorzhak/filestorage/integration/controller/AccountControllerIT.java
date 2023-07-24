package ru.oorzhak.filestorage.integration.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.oorzhak.filestorage.Application;
import ru.oorzhak.filestorage.dto.AccountDTO;
import ru.oorzhak.filestorage.repository.UserRepository;
import ru.oorzhak.filestorage.service.AccountService;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@TestPropertySource("classpath:test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class
)
@AutoConfigureMockMvc
public class AccountControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountService accountService;
    @MockBean
    private AmazonS3Client amazonS3Client;

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
    public void registerUser() {
        accountService.save(AccountDTO.builder()
                .username("username")
                .password("password")
                .confirmPassword("password")
                .build()
        );

//        mockMvc.perform(
//                    post("/accounts")
//                    .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .
    }
}

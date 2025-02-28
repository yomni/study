package io.yomni.testcontainer.container;

import io.restassured.RestAssured;
import io.yomni.testcontainer.domain.Member;
import io.yomni.testcontainer.infrastructure.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import redis.clients.jedis.Jedis;

import java.util.List;

import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItems;

@ActiveProfiles("test")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberIntegrationTest {

    @Container
    @ServiceConnection
    public static final GenericContainer<?> REDIS_CONTAINER = new GenericContainer("redis:7.0.5")
            .withExposedPorts(6379);

    @Autowired
    private MemberRepository repository;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        // given
        RestAssured.port = port;

        repository.saveAll(
                List.of(
                        new Member(null, "Frodo"),
                        new Member(null, "Samwise")
                )
        );
    }

    @AfterEach
    void afterAll() {
        // given
        repository.deleteAll();
    }

    @Test
    void findAllTest() {
        // when & then
        when().get("/members")
                .then().statusCode(200)
                .and().body("name", hasItems("Frodo", "Samwise"));
    }

    @Test
    void findAllTest2() {
        // when & then
        when().get("/members")
                .then().statusCode(200)
                .and().body("name", hasItems("Frodo", "Samwise"));
    }

    @Test
    void findAllTest3() {
        // when & then
        when().get("/members")
                .then().statusCode(200)
                .and().body("name", hasItems("Frodo", "Samwise"));
    }

    @Test
    void redisConnectionTest() {
        // given
        String redisKey = "test-key";
        String redisValue = "test-value";

        // Simulating Redis interaction logic (e.g., setting a key-value)
        var redisHost = REDIS_CONTAINER.getHost();
        var redisPort = REDIS_CONTAINER.getMappedPort(6379);

        try (var redisClient = new Jedis(redisHost, redisPort)) {
            // Execute Redis commands
            redisClient.set(redisKey, redisValue);
            String retrievedValue = redisClient.get(redisKey);

            // then
            assertThat(redisValue).isEqualTo(retrievedValue);
        }
    }
}

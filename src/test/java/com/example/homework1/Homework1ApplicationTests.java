package com.example.homework1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Homework1ApplicationTests {

    @Container
    private final GenericContainer<?> devapp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);

    @Container
    private final GenericContainer<?> prodapp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @Autowired
    public TestRestTemplate testRestTemplate;


    @Test
    void contextLoads() {
    }

    @Test
    public void devAppTest() {
        devapp.start();
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:" + devapp.getMappedPort(8080) + "/profile", String.class);
        Assertions.assertEquals("Current profile is dev", responseEntity.getBody());
    }

    @Test
    public void prodAppTest() {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:" + prodapp.getMappedPort(8081) + "/profile", String.class);
        Assertions.assertEquals("Current profile is production", responseEntity.getBody());
    }
}

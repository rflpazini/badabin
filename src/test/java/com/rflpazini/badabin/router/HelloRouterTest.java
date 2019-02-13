package com.rflpazini.badabin.router;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.rflpazini.badabin.BadabinApplication;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BadabinApplication.class, webEnvironment = RANDOM_PORT)
public class HelloRouterTest {

  @Autowired WebTestClient webTestClient;

  @Test
  @Ignore
  public void testExampleWithParameter() {
    this.webTestClient
        .get()
        .uri("/badadin?name=Rafael")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(String.class)
        .isEqualTo("Welcome to a world of surprises! Rafael");
  }

  @Test
  @Ignore
  public void testExampleWithoutParameter() {
    this.webTestClient
        .get()
        .uri("/badadin")
        .accept(MediaType.TEXT_PLAIN)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(String.class)
        .isEqualTo("Welcome to a world of surprises! Stranger...");
  }

  @Test
  public void testExampleWithError() {
    this.webTestClient
        .get()
        .uri("/badadin/something")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isNotFound();
  }
}

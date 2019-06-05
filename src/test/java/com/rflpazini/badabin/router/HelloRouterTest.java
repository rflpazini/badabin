package com.rflpazini.badabin.router;

import com.rflpazini.badabin.handler.HelloHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@State(Scope.Benchmark)
@WebFluxTest(controllers = {HelloHandler.class, HelloRouter.class})
public class HelloRouterTest {

  @Autowired WebTestClient webTestClient;

  @Test
  @Benchmark
  public void testBadabinEndPoint_withUrlParameter() {

    this.webTestClient
        .get()
        .uri("/badabin?name=Rafael")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("content", "Welcome to a world of surprises, Rafael");
  }

  @Test
  @Benchmark
  public void testBadabinEndPoint_withoutParameter() {
    this.webTestClient
        .get()
        .uri("/badabin")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("content", "Welcome to a world of surprises, Stranger...");
  }

  @Test
  @Benchmark
  public void testBadabinEndPoint_withError() {
    this.webTestClient.get().uri("/badabin/something").exchange().expectStatus().isNotFound();
  }
}

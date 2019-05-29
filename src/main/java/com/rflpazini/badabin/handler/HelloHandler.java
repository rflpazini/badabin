package com.rflpazini.badabin.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.rflpazini.badabin.domain.Greeting;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class HelloHandler {

  private static final String TEMPLATE_MESSAGE = "Welcome to a world of surprises, %s";
  private final AtomicLong counter = new AtomicLong();

  public Mono<ServerResponse> sayHello(ServerRequest request) {
    String name = request.queryParam("name").orElse("Stranger...");

    Greeting welcomeGreeting =
        new Greeting(
            counter.incrementAndGet(), String.format(TEMPLATE_MESSAGE, name), "Badadin", "v0.1");

    return ok().contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(welcomeGreeting));
  }

  public Mono<ServerResponse> saySomething(ServerRequest request) {
    final int ITERATIONS = Math.max(Integer.getInteger("iterations", 1), 1);

    String sentence = String.join(" ");

    for (int iter = 0; iter < ITERATIONS; iter++) {
      if (ITERATIONS != 1) {
        System.out.println("-- iteration " + (iter + 1) + " --");
      }

      long total = 0, start = System.currentTimeMillis(), last = start;

      for (int i = 1; i < 10_000_000; i++) {
        total += sentence
          .chars()
          .filter(Character::isUpperCase)
          .count();
        if (i % 1_000_000 == 0) {
          long now = System.currentTimeMillis();
          System.out.printf("%d (%d ms)%n", i / 1_000_000, now - last);
          last = now;
        }
      }

      System.out.printf("total: %d (%d ms)%n", total, System.currentTimeMillis() - start);
    }

    return ok().contentType(MediaType.APPLICATION_JSON)
      .body(BodyInserters.fromObject("Confirmed"));
  }
}

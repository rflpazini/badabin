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

  private static final String template = "Welcome to a world of surprises! %s";
  private final AtomicLong counter = new AtomicLong();

  public Mono<ServerResponse> sayHello(ServerRequest request) {
    String name = request.queryParam("name").orElse("Stranger...");

    Greeting welcomeGreeting =
        new Greeting(counter.incrementAndGet(), String.format(template, name), "Badadin", "v0.1");

    return ok().contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(welcomeGreeting));
  }
}

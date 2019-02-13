package com.rflpazini.badabin.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import com.rflpazini.badabin.handler.HelloHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class HelloRouter {

  @Bean
  public RouterFunction<ServerResponse> helloRoute(HelloHandler helloHandler) {
    return RouterFunctions.route(
        GET("/badabin").and(accept(MediaType.APPLICATION_JSON)), helloHandler::sayHello);
  }
}

package com.rflpazini.badabin.router;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.rflpazini.badabin.handler.SaleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class SaleRouter {
  @Bean
  public RouterFunction<ServerResponse> saleRoute(SaleHandler saleHandler) {
    return route(POST("/sale").and(accept(APPLICATION_JSON)), saleHandler::postMessage)
      .andRoute(GET("/sale"), saleHandler::getMessages)
      .andRoute(GET("/ab"), saleHandler::hi);
  }
}


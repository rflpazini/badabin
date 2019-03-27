package com.rflpazini.badabin.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import com.rflpazini.badabin.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ProductRouter {

  @Bean
  public RouterFunction<ServerResponse> productRoute(ProductHandler productHandler) {
    return RouterFunctions.route(
        GET("/products").and(accept(MediaType.APPLICATION_JSON)), productHandler::getAllProducts);
  }
}

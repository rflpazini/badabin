package com.rflpazini.badabin.router;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.rflpazini.badabin.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ProductRouter {

  @Bean
  public RouterFunction<ServerResponse> productRoute(ProductHandler productHandler) {
    return route(GET("/product"), productHandler::getAllProducts)
        .andRoute(GET("/product/{id}"), productHandler::getById)
        .andRoute(POST("/product").and(accept(APPLICATION_JSON)), productHandler::create);
  }
}

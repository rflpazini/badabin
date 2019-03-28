package com.rflpazini.badabin.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.rflpazini.badabin.domain.Product;
import com.rflpazini.badabin.service.ProductService;
import java.util.stream.Stream;
import javax.swing.text.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductHandler {

  private final ProductService mService;

  public Mono<ServerResponse> getAllProducts(ServerRequest request) {
    Flux<Product> allProducts = mService.findAll();
    return ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(allProducts, Product.class);
  }

  public Mono<ServerResponse> getById(ServerRequest request) {
    long id = Long.parseLong(request.pathVariable("id"));

    return ok().contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(mService.findById(id), Product.class);
  }

  public Mono<ServerResponse> create(ServerRequest request) {
    return request
        .bodyToMono(Product.class)
        .flatMap(mService::create)
        .flatMap(
            product ->
                ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(BodyInserters.fromObject(product)));
  }
}

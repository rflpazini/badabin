package com.rflpazini.badabin.service;

import com.rflpazini.badabin.domain.Product;
import com.rflpazini.badabin.repository.ProductRepository;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
@Import(ProductService.class)
public class ProductServiceTest {

  private final ProductService service;
  private final ProductRepository repository;

  public ProductServiceTest(
      @Autowired ProductService service, @Autowired ProductRepository repository) {
    this.service = service;
    this.repository = repository;
  }

  @Test
  public void testFindAllProduts() {
    Flux<Product> mockedProducts =
        repository.saveAll(
            Flux.just(
                new Product(
                    10000000001L,
                    "Blastoise",
                    "Blastoise is a Water type Pokémon introduced in Generation 1. It is known as the Shellfish Pokémon",
                    230.9),
                new Product(
                    10000000002L,
                    "Bulbasaur",
                    "Bulbasaur is a Grass/Poison type Pokémon introduced in Generation 1. It is known as the Seed Pokémon",
                    550.0),
                new Product(
                    10000000003L,
                    "Nidoran",
                    "Nidoran♀ is a Poison type Pokémon introduced in Generation 1. It is known as the Poison Pin Pokémon",
                    150.5)));

    Flux<Product> composite = service.findAll().thenMany(mockedProducts);

    Predicate<Product> expected =
        product -> mockedProducts.any(saveItem -> saveItem.equals(product)).block();

    StepVerifier.create(composite)
        .expectNextMatches(expected)
        .expectNextMatches(expected)
        .expectNextMatches(expected)
        .verifyComplete();
  }
}

package com.rflpazini.badabin.repository;

import com.rflpazini.badabin.domain.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

  Mono<Product> findById(long id);
}

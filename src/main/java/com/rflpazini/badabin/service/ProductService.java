package com.rflpazini.badabin.service;

import com.rflpazini.badabin.domain.Product;
import com.rflpazini.badabin.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository mRepository;

  public Mono<Product> create(Product product) {
    return Mono.just(product).flatMap(mRepository::save);
  }

  public Mono<Product> findById(long id) {
    return Mono.just(id).flatMap(mRepository::findById);
  }

  public Flux<Product> findAll() {
    return this.mRepository.findAll();
  }
}

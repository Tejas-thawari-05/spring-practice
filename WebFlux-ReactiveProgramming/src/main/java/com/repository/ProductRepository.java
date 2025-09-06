package com.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.Product;

import reactor.core.publisher.Mono;


@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer>{

	public Mono<Void> deleteProductById(int id);
}

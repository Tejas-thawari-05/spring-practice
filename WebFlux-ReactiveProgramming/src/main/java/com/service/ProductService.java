package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Product;
import com.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

	@Autowired
	 private  ProductRepository productRepository;
	    
	    
	public Mono<Product> createProduct(Product product){
		return productRepository.save(product);
	}
	
	public Flux<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	public Mono<Product> getProductById(int id){
		return productRepository.findById(id);
	}
	
	public Mono<Void> deleteProduct(int id){
		return productRepository.deleteProductById(id);
	}
	
	public Mono<Product> updateProduct(int id, Product product){
		return productRepository.findById(id)
				.flatMap(existingProduct -> {
					existingProduct.setName(product.getName());
					existingProduct.setPrice(product.getPrice());
					return productRepository.save(existingProduct);
				});
	}
	
	
	

	
	public Mono<Void> deleteAllProduct(){
		return productRepository.deleteAll();
	}
}












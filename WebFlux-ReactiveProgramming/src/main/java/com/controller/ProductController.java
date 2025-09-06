package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Product;
import com.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
//	@Autowired
//	private ProductRepository productRepository;
	
	@PostMapping
	public Mono<Product> createProduct(@RequestBody Product product){
		return productService.createProduct(product);
	}
	
	@GetMapping
	public Flux<Product> getAllProduct(){
		return productService.getAllProduct();
	}
	
	@GetMapping("/{id}")
	public Mono<Product> getProductById(@PathVariable int id){
		return productService.getProductById(id);
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> deleteProductById(@PathVariable int id){
		return productService.deleteProduct(id);
	}
	
	@DeleteMapping
	public Mono<Void> deleteAllProduct(){
		return productService.deleteAllProduct();
	}
	
	@PutMapping("/{id}")
	public Mono<Product> updateProduct(@PathVariable int id ,@RequestBody Product product){
		return productService.updateProduct(id, product);
	}
	
	
	
	
	
	
	
//	@PostMapping
//	public Mono<Product> createProduct(@RequestBody Product product){
//		return productRepository.save(product);
//	}
//	
//	@GetMapping
//	public Flux<Product> getAllProduct(){
//		return productRepository.findAll();
//	}
//	
//	@GetMapping("/{id}")
//	public Mono<Product> getProductById(@PathVariable int id){
//		return productRepository.findById(id);
//	}
//	
//	@DeleteMapping("/{id}")
//	public Mono<Void> deleteProduct(@PathVariable int id){
//		return productRepository.deleteProductById(id);
//	}
//	
//	@PutMapping("/{id}")
//	public Mono<Product> updateProduct(@PathVariable int id, @RequestBody Product product){
//		return productRepository.findById(id)
//				.flatMap(existingProduct -> {
//					existingProduct.setName(product.getName());
//					existingProduct.setPrice(product.getPrice());
//					return productRepository.save(existingProduct);
//				});
//	}
//	
//	@DeleteMapping
//	public Mono<Void> deleteAllProduct(){
//		return productRepository.deleteAll();
//	}
	
}











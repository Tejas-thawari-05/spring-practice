package com.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Product;
import com.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}
	
	
	@GetMapping("/{id}")
	public Product getProduct(@PathVariable int id) {
		return productService.getProductById(id);
	}
	
	@GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
	
	
	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable int id) {
		productService.deleteProduct(id);
		
		return "Product deleted and removed from cache";
	}
	
	
    @GetMapping("/cache")
    public Collection<Product> getAllCachedProducts() {
        return productService.getAllCachedProducts();
    }

    
    @DeleteMapping("/cache/clear")
    public String clearCache() {
        productService.clearCache();
        return "Cache cleared successfully";
    }
	
}

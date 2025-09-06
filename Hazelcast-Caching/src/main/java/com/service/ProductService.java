package com.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.entity.Product;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private  HazelcastInstance hazelcastInstance;
	
	@Cacheable(value = "products" , key = "#id")
	public Product getProductById(int id) {
		//System.out.println("Fetching from Database : Product Id : "+id);
		return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	}
	
	@CachePut(value = "products" , key = "#product.id")
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	@CacheEvict(value = "products" , key = "#id")
	public void deleteProduct(int id) {
		productRepository.deleteById(id);
	}
	
	@Cacheable(value = "products")
    public List<Product> getAllProducts() {
        //System.out.println("Fetching all products from DB...");
        return productRepository.findAll();
    }
	
	 // Fetch all cached products from Hazelcast
    public Collection<Product> getAllCachedProducts() {
        IMap<Long, Product> cache = hazelcastInstance.getMap("products");
        return cache.values();
    }

    // Clear cache manually
    public void clearCache() {
        IMap<Long, Product> cache = hazelcastInstance.getMap("products");
        cache.clear();
    }
	
}

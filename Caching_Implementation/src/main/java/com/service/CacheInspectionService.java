package com.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheInspectionService {

	@Autowired
	private CacheManager cacheManager;
	
	public String printCacheContent(String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		
		if(cache != null) {
			return Objects.requireNonNull(cache.getNativeCache()).toString();
		}else {
			return "No Cache Data Found for id :- "+cacheName;
		}
	
	}
}

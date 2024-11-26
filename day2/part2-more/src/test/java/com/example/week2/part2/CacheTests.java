package com.example.week2.part2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import com.example.week2.part2.CacheTests.Config;

@SpringBootTest(classes = Config.class)
// TODO: Hint - check how caches work https://docs.spring.io/spring-framework/reference/integration/cache/annotations.html
class CacheTests {

	@Autowired
	ProductionCachingService cachingService;

	@Test
	void should_return_value_from_cache() {
		// TODO: How will you check that the value is indeed taken from the cache?
	}

	@Test
	void should_evict_cache_by_key() {
		// TODO: How will you ensure that the value got evicted?
	}

	@SpringBootConfiguration(proxyBeanMethods = false)
	@EnableCaching
	static class Config {

		@Bean
		CacheManager cacheManager() {
			return new ConcurrentMapCacheManager();
		}

		@Bean
		ProductionCachingService cachingService() {
			return new ProductionCachingService();
		}
	}
}

class ProductionCachingService {

	@Cacheable("cachingService")
	public int multiplyByTwo(int input) {
		return input * 2;
	}

	@CacheEvict("cachingService")
	public void evict(int key) {
		System.out.println("EVICTED: " + key);
	}
}

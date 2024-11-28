package com.example.week2.part2.done;

import static org.assertj.core.api.Assertions.assertThat;

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

@SpringBootTest(classes = CacheTests.Config.class)
class CacheTests {

	@Autowired
	ProductionCachingService cachingService;

	@Autowired
	CacheManager cacheManager;

	@Test
	void should_return_value_from_cache() {
		int cachedResult = cachingService.multiplyByTwo(10);

		assertThat(cachedResult).isEqualTo(20);
		assertThat(valueFromCache(10)).isEqualTo(20);
	}

	@Test
	void should_evict_cache_by_key() {
		int cachedResult = cachingService.multiplyByTwo(20);
		assertThat(cachedResult).isEqualTo(40);
		assertThat(valueFromCache(20)).isEqualTo(40);

		cachingService.evict(20);

		assertThat(valueFromCache(20)).isNull();
	}

	private Integer valueFromCache(int key) {
		return cacheManager.getCache("cachingService").get(key, Integer.class);
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

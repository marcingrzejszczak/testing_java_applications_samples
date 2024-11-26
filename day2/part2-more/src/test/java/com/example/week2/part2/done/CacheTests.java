package com.example.week2.part2.done;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.context.annotation.Primary;

@SpringBootTest(classes = CacheTests.Config.class)
class CacheTests {

	@Autowired
	TestProductionCachingService cachingService;

	@Test
	void should_return_value_from_cache() {
		int firstCall = cachingService.multiplyByTwo(10);
		int secondCall = cachingService.multiplyByTwo(10);

		assertThat(firstCall).isEqualTo(secondCall);
	}

	@Test
	void should_evict_cache_by_key() {
		cachingService.multiplyByTwo(20);
		int firstCallToNumber2 = cachingService.multiplyByTwo(30);

		cachingService.evict(20);

		assertThat(cachingService.multiplyByTwo(30)).isEqualTo(firstCallToNumber2);
		assertThatExceptionOfType(IllegalStateException.class)
				.isThrownBy(() -> cachingService.multiplyByTwo(20)).withMessage("BOOM!");
	}

	@SpringBootConfiguration(proxyBeanMethods = false)
	@EnableCaching
	static class Config {

		@Bean
		CacheManager cacheManager() {
			return new ConcurrentMapCacheManager();
		}

		@Bean
		@Primary // Assuming that there's been one in production
		ProductionCachingService cachingService() {
			return new TestProductionCachingService();
		}
	}
}

class TestProductionCachingService extends ProductionCachingService {
	private final Map<Integer, Integer> counterMap = new HashMap<>();

	@Override
	public int multiplyByTwo(int input) {
		Integer counter = counterMap.getOrDefault(input, 0);
		if (counter == 0) {
			counterMap.put(input, ++counter);
			return super.multiplyByTwo(input);
		}
		throw new IllegalStateException("BOOM!");
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

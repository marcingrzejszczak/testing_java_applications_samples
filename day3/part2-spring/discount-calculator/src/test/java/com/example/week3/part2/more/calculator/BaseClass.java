package com.example.week3.part2.more.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

// TODO: Use the @WebMvcTest test slice with 1 controller
// TODO: Use @ContextConfiguration with configuration from the bottom
// TODO: Undisable
@Disabled
public class BaseClass {

	// TODO: Autowire the MockMvcBuilder

	@BeforeEach
	void setup() {
		// TODO: Add the MockMvcBuilder
		// RestAssuredMockMvc.standaloneSetup();
	}

	@TestConfiguration(proxyBeanMethods = false)
	static class Config {

		// TODO: Create a DiscountCalculator bean that will set a fixed value of discount rate for person
		@Bean
		DiscountCalculator testDiscountCalculator() {
			// TODO: Fix me
			return new DiscountCalculator(null);
		}
	}
}


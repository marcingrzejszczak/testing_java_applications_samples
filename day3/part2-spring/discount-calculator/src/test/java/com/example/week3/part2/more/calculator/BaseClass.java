package com.example.week3.part2.more.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

import com.example.week3.part2.more.calculator.BaseClass.Config;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

// TODO: Use the @WebMvcTest test slice with 1 controller
// TODO: Use @ContextConfiguration with configuration from the bottom
// TODO: Undisable
@Disabled
public class BaseClass {

	// TODO: Autowire the DiscountController

	@BeforeEach
	void setup() {
		// TODO: Add the discount controller bean
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


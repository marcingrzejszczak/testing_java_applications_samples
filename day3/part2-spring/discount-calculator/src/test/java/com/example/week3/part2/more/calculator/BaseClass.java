package com.example.week3.part2.more.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

// TODO: A SpringBootTest with Config from below
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

		// TODO: Create a bean that will set a fixed value of discount rate for person
		@Bean
		DiscountApplier testDiscountApplier() {
			return person -> { };
		}
	}
}


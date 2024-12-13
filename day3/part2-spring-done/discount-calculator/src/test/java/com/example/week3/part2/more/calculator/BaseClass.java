package com.example.week3.part2.more.calculator;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

import com.example.week3.part2.more.calculator.BaseClass.Config;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest(controllers = DiscountController.class)
@ContextConfiguration(classes = Config.class)
public class BaseClass {

	@Autowired
	DiscountController discountController;

	@BeforeEach
	void setup() {
		RestAssuredMockMvc.standaloneSetup(discountController);
	}

	@TestConfiguration(proxyBeanMethods = false)
	static class Config {

		@Bean
		DiscountCalculator testDiscountApplier() {
			return new DiscountCalculator(Collections.emptyList()) {
				@Override
				void calculateTotalDiscountRate(Person person) {
					person.setDiscountRate(10);
				}
			};
		}
	}
}


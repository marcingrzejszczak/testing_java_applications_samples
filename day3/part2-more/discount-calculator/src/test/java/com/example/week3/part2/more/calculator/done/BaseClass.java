package com.example.week3.part2.more.calculator.done;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.example.week3.part2.more.calculator.DiscountApplier;
import com.example.week3.part2.more.calculator.DiscountController;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest(classes = BaseClass.Config.class)
@Disabled("This is a demo of how it could be done")
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
		DiscountApplier testDiscountApplier() {
			return person -> person.setDiscountRate(10);
		}
	}
}


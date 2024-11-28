package com.example.week3.part2.more.calculator;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscountController {
	private final DiscountCalculator discountCalculator;

	DiscountController(DiscountCalculator discountCalculator) {
		this.discountCalculator = discountCalculator;
	}

	@PostMapping("/discount")
	DiscountResponse discount(@RequestBody Person person) {
		this.discountCalculator.calculateTotalDiscountRate(person);
		return new DiscountResponse(person.getName(), person.getDiscountRate());
	}
}

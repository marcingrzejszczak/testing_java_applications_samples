package com.example.week2.part5;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class DiscountCalculator {

	private static final Logger log = LoggerFactory.getLogger(DiscountCalculator.class);

	private final List<DiscountApplier> discountAppliers;

	DiscountCalculator(List<DiscountApplier> discountAppliers) {
		this.discountAppliers = discountAppliers;
	}

	@Transactional
	DiscountDto calculateTotalDiscountRate(Person person) {
		log.info("Calculating discount rate for person [{}]", person);
		return new DiscountDto(person.getName(), person.getOccupation(), this.discountAppliers
				.stream()
				.map(discountApplier -> discountApplier.getDiscountRate(person))
				.reduce(0D, Double::sum));
	}

}

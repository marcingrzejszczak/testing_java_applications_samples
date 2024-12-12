package com.example.week2.part5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class OccupationDiscountApplier implements DiscountApplier {

	private static final Logger log = LoggerFactory.getLogger(OccupationDiscountApplier.class);

	private final SpringDataDiscountService service;

	OccupationDiscountApplier(SpringDataDiscountService service) {
		this.service = service;
	}

	@Override
	public double getDiscountRate(Person person) {
		log.info("Calculating occupation discount");
		return service.getDiscountRate(person.getOccupation());
	}
}

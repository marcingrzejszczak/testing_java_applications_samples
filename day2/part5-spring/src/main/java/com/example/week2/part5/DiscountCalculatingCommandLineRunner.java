package com.example.week2.part5;

import org.springframework.boot.CommandLineRunner;

class DiscountCalculatingCommandLineRunner implements CommandLineRunner {

	private final String personName;

	private final int noOfGoods;

	private final Occupation occupation;

	private final DiscountCalculator discountCalculator;

	DiscountCalculatingCommandLineRunner(String personName, int noOfGoods, Occupation occupation, DiscountCalculator discountCalculator) {
		this.personName = personName;
		this.noOfGoods = noOfGoods;
		this.occupation = occupation;
		this.discountCalculator = discountCalculator;
	}

	@Override
	public void run(String... args) throws Exception {
		calculateDiscount();
	}

	DiscountDto calculateDiscount() {
		return discountCalculator.calculateTotalDiscountRate(new Person(personName, noOfGoods, occupation));
	}
}

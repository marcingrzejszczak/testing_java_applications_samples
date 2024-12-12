package com.example.week2.part5;

public class DiscountDto {
	private final String name;

	private final Occupation occupation;

	private final double rate;

	public DiscountDto(String name, Occupation occupation, double rate) {
		this.name = name;
		this.occupation = occupation;
		this.rate = rate;
	}

	public double getRate() {
		return rate;
	}

	public String getName() {
		return name;
	}

	public Occupation getOccupation() {
		return occupation;
	}
}

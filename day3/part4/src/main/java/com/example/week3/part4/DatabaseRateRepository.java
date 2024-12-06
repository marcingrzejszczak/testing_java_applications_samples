package com.example.week3.part4;

public class DatabaseRateRepository implements RateRepository {
	@Override
	public double getDiscountRate(Occupation occupation) {
		return OccupationUtils.getRateForOccupation(occupation);
	}
}

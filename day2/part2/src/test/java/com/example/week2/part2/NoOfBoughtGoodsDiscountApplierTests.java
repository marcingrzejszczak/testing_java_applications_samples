package com.example.week2.part2;

class NoOfBoughtGoodsDiscountApplierTests {

	// TODO: Implement me using a CsvSource with a text block
	/**
	 * @param noOfGoods number of goods that a person bought
	 * @param expectedDiscountRate expected discount rate
	 */
	void should_calculate_rate_based_on_number_of_goods(int noOfGoods, double expectedDiscountRate) {
	}

	private static Person personWithNoOfGoods(int noOfGoods) {
		return new Person("name", noOfGoods, Occupation.UNEMPLOYED);
	}
}

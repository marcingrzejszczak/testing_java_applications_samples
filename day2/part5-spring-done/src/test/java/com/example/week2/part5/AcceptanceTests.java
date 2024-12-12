package com.example.week2.part5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.sql.SQLException;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
class AcceptanceTests {

	@Container
	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer(DockerImageName.parse("mysql:9.1.0"));

	@Autowired
	DiscountRepository discountRepository;

	@Autowired
	DiscountCalculator discountCalculator;

	@Test
	void should_calculate_a_discount()  {
		discountRepository.save(new Discount("for employed", Occupation.EMPLOYED, 10));

		DiscountDto calculatedDiscount = discountCalculator.calculateTotalDiscountRate(new Person("foo", 100, Occupation.EMPLOYED));

		then(calculatedDiscount.getRate()).isPositive();
	}

}

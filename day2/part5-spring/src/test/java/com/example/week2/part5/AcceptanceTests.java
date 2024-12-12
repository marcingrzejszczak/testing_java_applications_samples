package com.example.week2.part5;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.sql.SQLException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
// TODO: Undisable me
@Disabled
class AcceptanceTests {

	@Container
	// TODO: Something is missing here
	static MySQLContainer mySQLContainer = new MySQLContainer(DockerImageName.parse("mysql:9.1.0"));

	// TODO: Use SpringDataDiscountService and DiscountCalculator beans

	@Test
	void should_calculate_a_discount() {
		// TODO: Use the repository to store the data in the database

		// TODO: Use the discount calculator to retrieve the calculated discount

		// TODO: Ensure that the calculated discount is positive
	}

}

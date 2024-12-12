package com.example.week2.part5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.sql.SQLException;

// TODO: Use the @DataJpaTest slice
@ContextConfiguration(classes = SpringDataDiscountServiceTests.Config.class)
// TODO: Add Testcontainers support
// TODO: Set @Transactional to use propagation NOT_SUPPORTED
// TODO: Undisable me
@Disabled
class SpringDataDiscountServiceTests {

	// TODO: Sth is missing here...
	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer(DockerImageName.parse("mysql:9.1.0"));

	@Autowired
	TestSpringDataDiscountService service;

	@Autowired
	DiscountRepository discountRepository;

	@BeforeEach
	void setup() {
		service.setThrowException(false);
	}

	@Test
	void should_store_data_in_a_database() throws SQLException {
		// TODO: Create a discount name

		// TODO: Save discount through the service

		// TODO: Check through the repository that there's a discount with the name
	}

	@Test
	void should_get_discount_rate_from_db() throws SQLException {
		// TODO: Use repository to save the discount

		// TODO: Find the discount rate through the service

		// TODO: Ensure that the discount rate is positive
	}

	@Test
	void should_rollback_transaction() {
		service.setThrowException(true);

		// TODO: Assert an exception if we try to add a discount with some occupation

		// TODO: Assert that there's no discount for that occupation
	}

	@Test
	void should_fail_when_database_constraint_violated() {
		// TODO: Create a person with name "john"

		// TODO: Use the service to store it

		// TODO: Catch the DataIntegrityViolationException when trying to save it again
	}

	@TestConfiguration(proxyBeanMethods = false)
	static class Config {

		@Bean
		TestSpringDataDiscountService testSpringDataDiscountService(DiscountRepository discountRepository) {
			return new TestSpringDataDiscountService(discountRepository);
		}
	}

	static class TestSpringDataDiscountService extends SpringDataDiscountService {

		private boolean throwException;

		TestSpringDataDiscountService(DiscountRepository discountRepository) {
			super(discountRepository);
		}

		@Transactional
		@Override
		public void addDiscount(Person person) {
			super.addDiscount(person);
			// TODO: What do you think of this? Are there any problems with this?
			if (throwException) {
				throw new IllegalStateException("BOOM!");
			}
		}

		void setThrowException(boolean throwException) {
			this.throwException = throwException;
		}
	}
}

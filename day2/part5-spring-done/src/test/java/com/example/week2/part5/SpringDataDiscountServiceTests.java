package com.example.week2.part5;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@ContextConfiguration(classes = SpringDataDiscountServiceTests.Config.class)
@Testcontainers
@Transactional(
		propagation = Propagation.NOT_SUPPORTED) // Otherwise transaction is created automatically for all tests
class SpringDataDiscountServiceTests {

	@Container
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
		String discountName = "foo";

		service.addDiscount(new Person(discountName, 10, Occupation.EMPLOYED));

		assertThat(discountRepository.findDiscountByName(discountName)).isPresent();
	}

	@Test
	void should_get_discount_rate_from_db() throws SQLException {
		discountRepository.save(new Discount("bar", Occupation.WONT_TELL, 10));

		double discountRate = service.getDiscountRate(Occupation.WONT_TELL);

		assertThat(discountRate).isNotZero();
	}

	@Test
	void should_rollback_transaction() {
		service.setThrowException(true);
		BDDAssertions.thenThrownBy(() -> service.addDiscount(new Person("johnny", 100, Occupation.UNEMPLOYED)))
				.isInstanceOf(IllegalStateException.class).hasMessageContaining("BOOM!");

		assertThat(service.getDiscountRate(Occupation.UNEMPLOYED)).isZero();
	}

	@Test
	void should_fail_when_database_constraint_violated() {
		Person person = new Person("john", 100, Occupation.EMPLOYED);

		service.addDiscount(person);

		assertThatThrownBy(() -> service.addDiscount(person))
				.isInstanceOf(DataIntegrityViolationException.class);
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
			if (throwException) {
				throw new IllegalStateException("BOOM!");
			}
		}

		void setThrowException(boolean throwException) {
			this.throwException = throwException;
		}
	}
}

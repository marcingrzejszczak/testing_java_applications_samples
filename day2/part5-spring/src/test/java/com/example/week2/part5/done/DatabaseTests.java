package com.example.week2.part5.done;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@DataJpaTest(properties = "spring.jpa.hibernate.ddl-auto=update")
@ContextConfiguration(classes = DatabaseTests.Config.class)
@Testcontainers
@Transactional(
		propagation = Propagation.NOT_SUPPORTED) // Otherwise transaction is created automatically for all tests
class DatabaseTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TestService testService;

	@Container
	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer(DockerImageName.parse("mysql:9.1.0"));

	@Test
	void testRollbackScenario() {
		BDDAssertions.thenThrownBy(() -> testService.withTransaction(new User(null, "foo@foo.com")))
					 .isInstanceOf(IllegalStateException.class).hasMessageContaining("BOOM!");

		assertThat(userRepository.findByEmail("foo@foo.com")).isNull();
	}

	@Test
	void testDatabaseConstraints() {
		User user = new User(null, "duplicate@test.com");

		userRepository.save(user);

		assertThatThrownBy(() -> userRepository.save(new User(null, "duplicate@test.com")))
				.isInstanceOf(DataIntegrityViolationException.class);
	}

	@SpringBootConfiguration(proxyBeanMethods = false) // remove this when copying over
//	@TestConfiguration(proxyBeanMethods = false) // change to this when copying over
	@EnableAutoConfiguration // remove this when copying over
	static class Config {

		@Bean
		ProductionService productionService(UserRepository userRepository) {
			return new ProductionService(userRepository);
		}

		@Bean
		TestService testService(ProductionService productionService) {
			return new TestService(productionService);
		}

	}
}

interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
}

class ProductionService {
	private final UserRepository userRepository;

	ProductionService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	void save(User user) {
		userRepository.save(user);
	}
}

class TestService {
	private final ProductionService productionService;

	TestService(ProductionService productionService) {
		this.productionService = productionService;
	}

	@Transactional
	void withTransaction(User user) {
		productionService.save(user);
		throw new IllegalStateException("BOOM!");
	}
}

// Supporting entity
@Entity
@Table(name = "user2", uniqueConstraints = @UniqueConstraint(columnNames = { "EMAIL" }))
class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String email;

	protected User() {

	}

	User(Integer id, String email) {
		this.id = id;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

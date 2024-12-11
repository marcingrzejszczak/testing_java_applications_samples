package com.example.week2.part5.todo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.example.week2.part5.todo.DatabaseTests.Config;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@DataJpaTest(properties = "spring.jpa.hibernate.ddl-auto=update")
@ContextConfiguration(classes = Config.class)
@Testcontainers
@Transactional(propagation = Propagation.NOT_SUPPORTED) // Otherwise transaction is created automatically for all tests
class DatabaseTests {

	@Container
	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer(DockerImageName.parse("mysql:9.1.0"));

	@Test
	void should_not_store_user_when_exception_thrown_in_the_same_transaction() {
		// TODO: Reuse production service's transaction and throw an exception to rollback

		// TODO: Assert that there is no entry in the database
	}

	@Test
	void should_not_save_a_user_when_constraint_on_email_violated() {
		// TODO: Save user

		// TODO: Save user again with the same email

		// TODO: Assert that an exception was thrown
	}

	@TestConfiguration(proxyBeanMethods = false)
	static class Config {

		@Bean
		ProductionService productionService(UserRepository userRepository) {
			return new ProductionService(userRepository);
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

// Supporting entity
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "EMAIL" }))
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

package com.example.week3.part2.client;

import java.net.http.HttpClient;
import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.webservices.client.WebServiceClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;

@AutoConfigureStubRunner(stubsMode = StubsMode.LOCAL, ids = "com.example.testingworkshop:discount-calculator-spring-done")
@RestClientTest
class PersonClientTests {

	@Autowired
	ObjectMapper objectMapper;

	@StubRunnerPort("discount-calculator-spring-done")
	int discountCalculatorPort;

	PersonClient personClient;

	@BeforeEach
	void setup() {
		personClient = new PersonClient(HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(1)).build(),
													 "http://localhost:" + discountCalculatorPort,
													 objectMapper);
	}

	@Test
	void should_parse_the_response_for_ok_status() {
		Person person = new Person("foo", 5000, Occupation.EMPLOYED);

		DiscountResponse discountResponse = personClient.discount(person);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(discountResponse.personName()).isEqualTo("foo");
			softAssertions.assertThat(discountResponse.discountRate()).isEqualTo(10);
			softAssertions.assertThat(discountResponse.person()).isNull();
			softAssertions.assertThat(discountResponse.additionalMessage()).isNull();
		});
	}
}

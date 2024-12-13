package com.example.week3.part2.client;

import java.net.http.HttpClient;
import java.time.Duration;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import com.fasterxml.jackson.databind.ObjectMapper;

// TODO: Use @AutoConfigureStubRunner Local mode and maven coordinates of the other module
@RestClientTest
// TODO: Undisable me
@Disabled
class PersonClientTests {

	@Autowired
	ObjectMapper objectMapper;

	// TODO: @StubRunnerPort with the artifact id of the other module
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

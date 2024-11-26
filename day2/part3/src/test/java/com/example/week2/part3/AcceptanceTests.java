package com.example.week2.part3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

// TODO: IMPORTANT: For simplicity we're doing fixed port - if you have time consider using https://github.com/wiremock/wiremock-spring-boot
// TODO: Fix me - Start a WireMock instance on port 12345
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Week2Part3.class, properties = "person.url=http://localhost:12345")
class AcceptanceTests {

	@LocalServerPort
	int port;
	
	@Autowired
	ObjectMapper objectMapper;

	@BeforeEach
	void setupWireMock() {
		// TODO: Fix me - stub post @ /person/foo
				// TODO: Fix me - add application/json content-type matching
				// TODO: Fix me - add json body matching to person with name foo number of bought goods 100 and unemployed occupation
					// TODO: Fix me - Return a response
						// TODO: Fix me - add application/json content-type matching
						// TODO: Fix me - add json body matching to person details response of name foo, resource id "1" and discount status STORED

		// TODO: Fix me - stub get @ /person/1/discount
				 // TODO: Fix me - Return a response
						// TODO: Fix me - add application/json content-type matching
						// TODO: Fix me - add json body matching to person discount response of name foo and discount rate 10.5
	}

	@Test
	void should_calculate_discount_rate_for_person() {
		// TODO: Fix me - make an test http call to discount endpoint

		// TODO: Fix me - assert that the status code is OK and the discount body has name "foo" and 10.5 discount rate in it
	}

	private String toJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		}
		catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}

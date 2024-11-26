package com.example.week2.part3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.springframework.web.client.RestTemplate;

class PersonClientTests {

	private static final String EXPECTED_REFERENCE_ID = "1234";

	private static final String EXPECTED_PERSON_NAME = "smith";

	private static final PersonDetailsResponse.Status EXPECTED_STORED_RESPONSE_STATUS = PersonDetailsResponse.Status.STORED;

	// TODO: Fix me - register a WireMock JUnit 5 extension with a dynamic port and static dsl

	PersonClient personClient = new PersonClient(restTemplate(), "http://localhost:" + "TODO: Change me to a dynamic port from extension");

	Person mrSmith = mrSmith();

	private RestTemplate restTemplate() {
		return new DiscountConfiguration.ClientConfiguration().personRestTemplate(500, 500);
	}

	@Nested
	class PersonUpdateTests {
		@Test
		void should_return_person_details_response_when_not_in_manual_processing() {
			// TODO: Fix me - stub for post @ /person/smith
					// TODO: Fix me - add application/json content-type matching
					// TODO: Fix me - add json body matching to json mr smith
							// TODO: Fix me - add application/json content-type matching
							// TODO: Fix me - return "processing" response

			PersonDetailsResponse response = personClient.updatePersonDetails(mrSmith);

			// Soft Assertions assert everything and throw exception at the end with the list of all failing assertions
			// TODO: Add soft assertions from assertj
				// TODO: Fix me - assert that the fields contain proper values
		}

		@Test
		void should_fail_with_manual_processing_exception_for_manual_processing_status() {
			// TODO: Fix me - stub for post @ /person/smith
					// TODO: Fix me - add application/json content-type matching
					// TODO: Fix me - add json body matching to json mr smith
							// TODO: Fix me - add application/json content-type matching
							// TODO: Fix me - return "manual processing" response

			// TODO: Fix me - add assertion, PersonInManualProcessingException should be thrown
		}

		@Test
		void should_fail_with_timeout_from_server() {
			// TODO: Fix me - stub for post @ /person/smith
					// TODO: Fix me - add application/json content-type matching
					// TODO: Fix me - add json body matching to json mr smith
					// TODO: Add fixed delay of 1000

			// TODO: Fix me - add assertion, PersonNotStoredException should be thrown + check the root cause
		}

		@Test
		void should_fail_with_connection_reset_by_peer_from_server() {
			// TODO: Fix me - stub for post @ /person/smith
				// TODO: Add Fault.CONNECTION_RESET_BY_PEER fault

			// TODO: Fix me - add assertion, PersonNotStoredException should be thrown + check the root cause
		}

		@Test
		void should_fail_with_empty_response_from_server() {
			// TODO: Fix me - stub for post @ /person/smith
			// TODO: Add Fault.EMPTY_RESPONSE fault;

			// TODO: Fix me - add assertion, PersonNotStoredException should be thrown + check the root cause
		}

		@Test
		void should_fail_with_malformed_response_from_server() {
			// TODO: Fix me - stub for post @ /person/smith
			// TODO: Add Fault.MALFORMED_RESPONSE_CHUNK fault

			// TODO: Fix me - add assertion, PersonNotStoredException should be thrown + check the root cause
		}

		@Test
		void should_fail_with_random_data_then_close_from_server() {
			// TODO: Fix me - stub for post @ /person/smith
			// TODO: Add Fault.RANDOM_DATA_THEN_CLOSE fault

			// TODO: Fix me - add assertion, PersonNotStoredException should be thrown + check the root cause
		}

		private static PersonDetailsResponse storedProcessingResponse() {
			return new PersonDetailsResponse(EXPECTED_PERSON_NAME, EXPECTED_REFERENCE_ID, EXPECTED_STORED_RESPONSE_STATUS);
		}

		private static PersonDetailsResponse manualProcessingResponse() {
			return new PersonDetailsResponse(EXPECTED_PERSON_NAME, EXPECTED_REFERENCE_ID, PersonDetailsResponse.Status.MANUAL_PROCESSING);
		}
	}

	@Nested
	class PersonGetDiscountTests {
		@Test
		void should_return_discount_for_person() {
			// TODO: Fix me - stub for get @ /person/1/discount
					 // TODO: Fix me - response
							// TODO: Fix me - add application/json content-type matching
							// TODO: Fix me - add json body matching to discount response of mr smith with 10.5 discount rate

			double response = personClient.getDiscount("1");

			// TODO: Fix me - add assertion, should be 10.5
		}

		@Test
		void should_return_no_discount_with_timeout_from_server() {
			// TODO: Fix me - stub for get @ /person/1/discount
			// TODO: Fix me - response
					// TODO: Fix me - add application/json content-type matching
					// TODO: Add Fixed delay of 1000 millis

			double response = personClient.getDiscount("1");

			// TODO: Fix me - add assertion, should be 0
		}

		@Test
		void should_return_no_discount_with_connection_reset_by_peer_from_server() {
			// TODO: Fix me - stub for get @ /person/1/discount
			// TODO: Add Fault.CONNECTION_RESET_BY_PEER fault

			double response = personClient.getDiscount("1");

			// TODO: Fix me - add assertion, should be 0
		}

		@Test
		void should_return_no_discount_with_empty_response_from_server() {
			// TODO: Fix me - stub for get @ /person/1/discount
			// TODO: Add Fault.EMPTY_RESPONSE fault

			double response = personClient.getDiscount("1");

			// TODO: Fix me - add assertion, , should be 0should be 0
		}

		@Test
		void should_return_no_discount_with_malformed_response_from_server() {
			// TODO: Fix me - stub for get @ /person/1/discount
			// TODO: Add Fault.MALFORMED_RESPONSE_CHUNK fault

			double response = personClient.getDiscount("1");

			// TODO: Fix me - add assertion, should be 0
		}

		@Test
		void should_return_no_discount_with_random_data_then_close_from_server() {
			// TODO: Fix me - stub for get @ /person/1/discount
			// TODO: Add Fault.RANDOM_DATA_THEN_CLOSE fault

			double response = personClient.getDiscount("1");

			// TODO: Fix me - add assertion, should be 0
		}
	}

	private static String toJson(Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		}
		catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private static Person mrSmith() {
		return new Person(EXPECTED_PERSON_NAME, 10, Occupation.UNEMPLOYED);
	}

}

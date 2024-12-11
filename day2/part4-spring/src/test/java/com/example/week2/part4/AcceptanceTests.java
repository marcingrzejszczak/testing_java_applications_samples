package com.example.week2.part4;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;

class AcceptanceTests {

	@Nested
	class UnitTests {
		// TODO: Bonus
		@Test
		void should_throw_exception_when_not_enough_arguments_were_passed() {

		}
	}

	@Nested
			// TODO: Fix me - missing interface implementation with helper methods
	@Disabled
	class IntegrationTests {


		int goodsThreshold = 5;
		double goodsDiscountRate = 5D;
		int nameThreshold = 3;
		double nameDiscountRate = 8D;

		@Test
		void should_calculate_maximum_discount() {
			String inputMessage = null; // TODO: Fix me - read mrSmith.json
			String outputMessage = null; // TODO: Fix me - read mrSmithWithDiscountRate.json
			String inputQueue = "inputQueue";
			String outputQueue = "outputQueue";
			// TODO: Fix me - before tests a message should be sent out

			// TODO: Fix me - use Awaitility to assert if message was properly sent to broker
		}

	}
}

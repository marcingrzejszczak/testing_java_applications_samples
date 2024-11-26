package com.example.week2.part4;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

class TestRabbitmqMessageListener extends RabbitMqMessageListener {

	public TestRabbitmqMessageListener(String inputQueueName, ConnectionFactory factory, ObjectMapper objectMapper) {
		super(inputQueueName, factory, objectMapper);
	}

	@Override
	Person readMessage(GetResponse response) throws IOException {
		Person readMessage = super.readMessage(response);
		// TODO: set the read message in a field (remember about concurrency)
		return readMessage;
	}

	@Override
	boolean pollingCondition() {
		// TODO: Change to wait until there's at least one message
		return false;
	}
}

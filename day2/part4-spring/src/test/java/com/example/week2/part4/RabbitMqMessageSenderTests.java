package com.example.week2.part4;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(properties = "rabbitmq.output.queue=test-output-queue")
@Testcontainers
class RabbitMqMessageSenderTests {

	@Container
	// TODO: Fix me - sth is missing here, check https://docs.spring.io/spring-boot/reference/testing/testcontainers.html
	static RabbitMQContainer rabbitMQContainer = new RabbitMQContainer(DockerImageName.parse("rabbitmq:4.0.4"));

	@Autowired
	RabbitMqMessageSender rabbitMqMessageSender;

	@Autowired
	TestListener testListener;

	@Test
	void should_send_a_message_to_rabbit() {
		// TODO: Fix me - create a person

		// TODO: Fix me - send a message through RabbitMqMessageSender

		// TODO: Use Awaitility.await().untilAtomic(..., Matchers.equalTo(person));
	}

	// This is a test slice
	@SpringBootConfiguration(proxyBeanMethods = false)
	@ImportAutoConfiguration(RabbitAutoConfiguration.class)
	static class Config {

		@Bean
		Jackson2JsonMessageConverter messageConverter() {
			return new Jackson2JsonMessageConverter();
		}

		@Bean
		RabbitMqMessageSender rabbitMqMessageSender(@Value("${rabbitmq.output.queue}") String outputQueue,
													RabbitTemplate rabbitTemplate) {
			return new RabbitMqMessageSender(outputQueue, rabbitTemplate);
		}

		@Bean
		TestListener testListener() {
			return new TestListener();
		}
	}

	// TODO: Fix me - create a test listener that will receive a Person message from ${rabbitmq.output.queue}
	//  and store it in an AtomicReference
	static class TestListener {

		private static final Logger log = LoggerFactory.getLogger(TestListener.class);

		public void onMessage(Person person) {
			log.info("Received person {}", person);
			// TODO: set the value of Person in an AtomicReference
		}
	}

}

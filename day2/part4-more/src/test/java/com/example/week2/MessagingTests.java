package com.example.week2;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
class MessagingTests {

	// TODO: You'll need a RabbitTemplate in tests

	@Container
	// TODO: Fix me - sth is missing here, check https://docs.spring.io/spring-boot/reference/testing/testcontainers.html
	static RabbitMQContainer rabbitMQContainer = new RabbitMQContainer(DockerImageName.parse("rabbitmq:4.0.4"));

	@Test
	void should_receive_messages_in_the_same_order_as_sent() {
		// TODO: Hint check convertAndSend and receiveAndConvert methods on RabbitTemplate
	}

	@SpringBootConfiguration(proxyBeanMethods = false)
	@ImportAutoConfiguration(RabbitAutoConfiguration.class)
	static class Config {
		@Bean
		Queue testQueue() {
			return new Queue("test-queue", false);
		}

		@Bean
		TopicExchange testExchange() {
			return new TopicExchange("test-exchange");
		}

		@Bean
		Binding testBinding(@Qualifier("testQueue") Queue queue, @Qualifier("testExchange") TopicExchange exchange) {
			return BindingBuilder.bind(queue).to(exchange).with("#");
		}

	}
}

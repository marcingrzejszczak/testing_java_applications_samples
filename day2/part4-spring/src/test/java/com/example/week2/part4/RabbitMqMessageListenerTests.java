package com.example.week2.part4;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.example.week2.part4.RabbitMqMessageListenerTests.Config;

@SpringBootTest(properties = "rabbitmq.input.queue=test-queue", classes = Config.class)
@Testcontainers
// TODO: Undisable me
@Disabled
class RabbitMqMessageListenerTests {

	@Container
	// TODO: Fix me - sth is missing here, check https://docs.spring.io/spring-boot/reference/testing/testcontainers.html
	static RabbitMQContainer rabbitMQContainer = new RabbitMQContainer(DockerImageName.parse("rabbitmq:4.0.4"));

	@Autowired
	RabbitTemplate rabbitTemplate;

	// TODO: Maybe this could be a MockitoBean ?
	DiscountCalculator discountCalculator;

	@Value("${rabbitmq.input.queue}")
	String inputQueueName;

	@Test
	void should_calculate_total_discount_and_send_a_message_to_broker() {
		// TODO: Hint check convertAndSend method on RabbitTemplate

		// TODO: Use Awaitility.await().untilAsserted() to ensure that calculation happened
	}

	// This is a test slice
	@TestConfiguration(proxyBeanMethods = false)
	@ImportAutoConfiguration(RabbitAutoConfiguration.class)
	static class Config {

		@Bean
		Jackson2JsonMessageConverter messageConverter() {
			return new Jackson2JsonMessageConverter();
		}

		// TODO: Fix me - uncoment me
		// @Bean
		RabbitMqMessageListener rabbitMqMessageListener(DiscountCalculator discountCalculator) {
			return new RabbitMqMessageListener(discountCalculator);
		}
	}

}

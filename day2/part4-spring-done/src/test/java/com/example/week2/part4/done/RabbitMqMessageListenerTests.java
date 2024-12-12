package com.example.week2.part4.done;

import static org.mockito.BDDMockito.then;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.example.week2.part4.done.RabbitMqMessageListenerTests.Config;

@SpringBootTest(properties = "rabbitmq.input.queue=test-queue",
		classes = Config.class)
@Testcontainers
class RabbitMqMessageListenerTests {

	@Container
	@ServiceConnection
	static RabbitMQContainer rabbitMQContainer
			= new RabbitMQContainer(DockerImageName.parse("rabbitmq:4.0.4"));

	@Autowired
	RabbitTemplate rabbitTemplate;

	@MockitoBean
	DiscountCalculator discountCalculator;

	@Value("${rabbitmq.input.queue}")
	String inputQueueName;

	@Test
	void should_calculate_total_discount_and_send_a_message_to_broker() {
		rabbitTemplate.convertAndSend(inputQueueName,
									  new Person("smith", 100, Occupation.EMPLOYED));

		Awaitility.await()
				  .untilAsserted(() ->
										 then(discountCalculator).should().calculateTotalDiscountRate(new Person("smith", 100, Occupation.EMPLOYED)));
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
		RabbitMqMessageListener rabbitMqMessageListener(DiscountCalculator discountCalculator) {
			return new RabbitMqMessageListener(discountCalculator);
		}
	}

}

package com.example.week2.part4.done;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
class MessagingTests {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Container
	@ServiceConnection
	static RabbitMQContainer rabbitMQContainer = new RabbitMQContainer(DockerImageName.parse("rabbitmq:4.0.4"));

	@Test
	void should_receive_messages_in_the_same_order_as_sent() {
		rabbitTemplate.convertAndSend("test-exchange",  "#", "Message 1");
		rabbitTemplate.convertAndSend("test-exchange", "#", "Message 2");

		String firstMessage = (String) rabbitTemplate.receiveAndConvert("test-queue");
		String secondMessage = (String) rabbitTemplate.receiveAndConvert("test-queue");

		assertThat(firstMessage).isEqualTo("Message 1");
		assertThat(secondMessage).isEqualTo("Message 2");
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

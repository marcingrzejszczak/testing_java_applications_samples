package com.example.week2.part4.done;

import java.util.concurrent.atomic.AtomicReference;

import org.awaitility.Awaitility;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(properties = "rabbitmq.output.queue=test-output-queue")
@Testcontainers
class RabbitMqMessageSenderTests {

	@Container
	@ServiceConnection
	static RabbitMQContainer rabbitMQContainer = new RabbitMQContainer(DockerImageName.parse("rabbitmq:4.0.4"));

	@Autowired
	RabbitMqMessageSender rabbitMqMessageSender;

	@Autowired
	TestListener testListener;

	@Test
	void should_send_a_message_to_rabbit() {
		Person person = new Person("foo", 200, Occupation.UNEMPLOYED);

		rabbitMqMessageSender.sendMessage(person);

		Awaitility.await().untilAtomic(testListener.personHolder, Matchers.equalTo(person));
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
		RabbitMqMessageSender rabbitMqMessageSender(@Value("${rabbitmq.output.queue}") String outputQueue, RabbitTemplate rabbitTemplate) {
			return new RabbitMqMessageSender(outputQueue, rabbitTemplate);
		}

		@Bean
		TestListener testListener() {
			return new TestListener();
		}
	}


	static class TestListener {

		AtomicReference<Person> personHolder = new AtomicReference<>();

		private static final Logger log = LoggerFactory.getLogger(TestListener.class);

		@RabbitListener(queuesToDeclare = @Queue(name = "${rabbitmq.output.queue}"))
		public void onMessage(Person person) {
			log.info("Received person {}", person);
			personHolder.set(person);
		}
	}

}

package com.example.week2.part4.done;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
class RabbitMqMessageListener {

	private static final Logger log = LoggerFactory.getLogger(RabbitMqMessageListener.class);
	private final DiscountCalculator discountCalculator;

	RabbitMqMessageListener(DiscountCalculator discountCalculator) {
		this.discountCalculator = discountCalculator;
	}

	@RabbitListener(queuesToDeclare = @Queue(name = "${rabbitmq.input.queue}"))
	public void onMessage(Person person) {
		log.info("Received person {}", person);
		discountCalculator.calculateTotalDiscountRate(person);
	}

}

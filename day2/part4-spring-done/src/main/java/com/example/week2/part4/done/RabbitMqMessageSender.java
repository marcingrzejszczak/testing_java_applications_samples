package com.example.week2.part4.done;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class RabbitMqMessageSender implements MessageSender {

	private final String outputQueueName;

	private final RabbitTemplate rabbitTemplate;

	public RabbitMqMessageSender(@Value("${rabbitmq.output.queue}") String outputQueueName,
								 RabbitTemplate rabbitTemplate) {
		this.outputQueueName = outputQueueName;
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public void sendMessage(Object message) {
		rabbitTemplate.convertAndSend(this.outputQueueName, message);
	}

}

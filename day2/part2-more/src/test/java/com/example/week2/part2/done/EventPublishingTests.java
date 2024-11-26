package com.example.week2.part2.done;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

@SpringBootTest
@RecordApplicationEvents
class EventPublishingTests {

	@Autowired
	TestEventPublishingService testEventPublishingService;

	@Autowired
	ApplicationEvents events;

	@Test
	void should_publish_test_event() {
		testEventPublishingService.publishMessage(new TestEvent("Test event payload"));

		assertThat(events.stream(TestEvent.class).count()).isEqualTo(1);
	}

	@SpringBootConfiguration(proxyBeanMethods = false)
	static class Config {

		@Bean
		TestEventPublishingService testEventPublishingService(ApplicationEventPublisher publisher) {
			return new TestEventPublishingService(publisher);
		}
	}
}

class TestEvent {
	private final String payload;

	public TestEvent(String payload) {
		this.payload = payload;
	}

	public String getPayload() {
		return payload;
	}
}

class TestEventPublishingService {
	private final ApplicationEventPublisher eventPublisher;

	TestEventPublishingService(ApplicationEventPublisher eventPublisher) {this.eventPublisher = eventPublisher;}

	void publishMessage(TestEvent event) {
		eventPublisher.publishEvent(event);
	}
}

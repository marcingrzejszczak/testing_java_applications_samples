package com.example.week2.part2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

@SpringBootTest
//TODO: Fixme - check how to test events https://docs.spring.io/spring-framework/reference/testing/testcontext-framework/application-events.html
class EventPublishingTests {

	@Test
	void should_publish_event() {

	}

	@SpringBootConfiguration(proxyBeanMethods = false)
	static class Config {

		@Bean
		TestEventPublishingService testEventPublishingService(ApplicationEventPublisher publisher) {
			return new TestEventPublishingService(publisher);
		}
	}
}

// Supporting classes
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

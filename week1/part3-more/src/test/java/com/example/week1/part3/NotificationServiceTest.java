package com.example.week1.part3;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NotificationServiceTest {
	@Test
	void testNotification() throws NoSuchFieldException, IllegalAccessException {
		EmailService emailService = Mockito.mock(EmailService.class);
		SmsService smsService = Mockito.mock(SmsService.class);

		NotificationService service = new NotificationService();
		Field field1 = NotificationService.class.getDeclaredField("emailService");
		field1.setAccessible(true);
		field1.set(service, emailService);
		Field field2 = NotificationService.class.getDeclaredField("smsService");
		field2.setAccessible(true);
		field2.set(service, smsService);

		service.sendNotification("email", "Test message");
		Mockito.verify(emailService).sendEmail("Test message");

		service.sendNotification("sms", "Test message");
		Mockito.verify(smsService).sendSms("Test message");
	}
}

package com.example.week1.part3;

public class NotificationService {
	private EmailService emailService;
	private SmsService smsService;

	public boolean sendNotification(String userPreference, String message) {
		if ("email".equals(userPreference)) {
			emailService.sendEmail(message);
			return true;
		} else if ("sms".equals(userPreference)) {
			smsService.sendSms(message);
			return true;
		}
		return false;
	}
}

interface EmailService {
	void sendEmail(String message);
}

interface SmsService {
	void sendSms(String message);
}

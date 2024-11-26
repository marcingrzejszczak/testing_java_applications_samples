package com.example.week1.part2;

public class PaymentProcessor {
	private static boolean validatePayment(String paymentDetails) {
		return paymentDetails != null && paymentDetails.length() > 10;
	}

	private static void saveToDatabase(String paymentDetails) {
		try {
			Thread.sleep(1000); // Simulating a database operation
			System.out.println("Payment saved: " + paymentDetails);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean processPayment(String paymentDetails) {
		if (validatePayment(paymentDetails)) {
			saveToDatabase(paymentDetails);
			return true;
		} else {
			return false;
		}
	}
}

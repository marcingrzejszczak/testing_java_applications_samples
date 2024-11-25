package com.example.week1.part4;

import java.util.function.Consumer;

public class PaymentScheduler {
	public void schedulePayment(Double amount) {
		Thread thread = new Thread(() -> amountProcessor().accept(amount));
		thread.start();
	}

	Consumer<Double> amountProcessor() {
		return aDouble -> System.out.println("paid [" + aDouble + "]");
	}

	public void checkPayment(Double amount) {
		if (amount <= 0) {
			throw new PaymentException("Payment failed");
		}
	}
}

class PaymentException extends RuntimeException {
	public PaymentException(String message) {
		super(message);
	}
}

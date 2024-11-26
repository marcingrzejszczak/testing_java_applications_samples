package com.example.week1.part2;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class PaymentProcessorTest {

	@Test
	public void paymentSaveTest() {
		// Improper testing of static method
		assertTrue(new PaymentProcessor().processPayment("validpayment123"));
	}
}

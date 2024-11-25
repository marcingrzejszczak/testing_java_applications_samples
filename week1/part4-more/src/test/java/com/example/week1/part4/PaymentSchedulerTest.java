package com.example.week1.part4;

import org.junit.jupiter.api.Test;

public class PaymentSchedulerTest {
    @Test
    public void testPaymentScheduling() {
        PaymentScheduler scheduler = new PaymentScheduler();

        scheduler.schedulePayment(5D);
    }

    @Test
    public void testPaymentFailure() {
        PaymentScheduler scheduler = new PaymentScheduler();
        scheduler.checkPayment(4D);
    }
}

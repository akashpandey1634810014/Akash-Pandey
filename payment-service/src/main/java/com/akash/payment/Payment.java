package com.akash.payment;

import java.time.LocalDateTime;

public record Payment(
        Long id,
        String invoiceNo,
        String customerName,
        PaymentMethod method,
        double amount,
        String mode,
        LocalDateTime paidAt
) {
}

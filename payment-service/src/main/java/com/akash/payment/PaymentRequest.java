package com.akash.payment;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PaymentRequest(
        @NotBlank(message = "invoiceNo is required") String invoiceNo,
        @NotBlank(message = "customerName is required") String customerName,
        @NotNull(message = "payment method is required") PaymentMethod method,
        @Min(value = 1, message = "amount must be at least 1") double amount,
        @NotBlank(message = "mode is required") String mode
) {
}

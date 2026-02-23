package com.akash.payment;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PaymentService {
    private final AtomicLong sequence = new AtomicLong(1);
    private final Map<Long, Payment> payments = new ConcurrentHashMap<>();

    public Payment save(PaymentRequest request) {
        if (!"OFFLINE".equalsIgnoreCase(request.mode())) {
            throw new IllegalArgumentException("Only OFFLINE mode is supported in this system");
        }
        long id = sequence.getAndIncrement();
        Payment payment = new Payment(id, request.invoiceNo(), request.customerName(), request.method(), request.amount(), "OFFLINE", LocalDateTime.now());
        payments.put(id, payment);
        return payment;
    }

    public List<Payment> all() {
        return payments.values().stream().sorted(Comparator.comparing(Payment::id)).toList();
    }
}

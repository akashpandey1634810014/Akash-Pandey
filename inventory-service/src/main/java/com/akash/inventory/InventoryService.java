package com.akash.inventory;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class InventoryService {
    private final AtomicLong sequence = new AtomicLong(1);
    private final Map<Long, Product> products = new ConcurrentHashMap<>();

    public InventoryService() {
        create(new Product(null, "Java Fundamentals", ProductType.BOOK, 120, 299));
        create(new Product(null, "A4 Notebook", ProductType.NOTEBOOK, 300, 80));
    }

    public Product create(Product request) {
        long id = sequence.getAndIncrement();
        Product product = new Product(id, request.name(), request.type(), request.quantity(), request.unitPrice());
        products.put(id, product);
        return product;
    }

    public List<Product> all() {
        return products.values().stream()
                .sorted(Comparator.comparing(Product::id))
                .toList();
    }

    public Product adjustStock(long id, int quantitySold) {
        Product existing = products.get(id);
        if (existing == null) {
            throw new IllegalArgumentException("Product not found: " + id);
        }
        int newQty = existing.quantity() - quantitySold;
        if (newQty < 0) {
            throw new IllegalArgumentException("Not enough stock for product id: " + id);
        }
        Product updated = new Product(id, existing.name(), existing.type(), newQty, existing.unitPrice());
        products.put(id, updated);
        return updated;
    }
}

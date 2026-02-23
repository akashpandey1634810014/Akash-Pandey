package com.akash.inventory;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@Validated
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/products")
    public List<Product> allProducts() {
        return inventoryService.all();
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@Valid @RequestBody Product product) {
        return inventoryService.create(product);
    }

    @PatchMapping("/products/{id}/sell")
    public Product sell(@PathVariable long id, @RequestParam @Min(1) int quantity) {
        return inventoryService.adjustStock(id, quantity);
    }
}

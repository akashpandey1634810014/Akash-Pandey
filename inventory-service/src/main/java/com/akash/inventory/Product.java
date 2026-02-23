package com.akash.inventory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Product(
        Long id,
        @NotBlank(message = "name is required") String name,
        @NotNull(message = "type is required") ProductType type,
        @Min(value = 0, message = "quantity should be 0 or greater") int quantity,
        @Min(value = 0, message = "unitPrice should be 0 or greater") double unitPrice
) {
}

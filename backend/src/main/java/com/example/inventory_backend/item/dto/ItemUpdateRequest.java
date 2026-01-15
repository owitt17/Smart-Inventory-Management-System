package com.example.inventory_backend.item.dto;

import jakarta.validation.constraints.Size;

public record ItemUpdateRequest(
        @Size(min = 1, max = 100) String name,
        Integer quantity
) {}

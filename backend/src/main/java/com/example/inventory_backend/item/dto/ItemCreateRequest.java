package com.example.inventory_backend.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ItemCreateRequest(
        @NotBlank @Size(min = 1, max = 100) String name,
        @NotNull Integer quantity
) {}

package com.example.inventory_backend.item.dto;

import java.time.Instant;

public record ItemResponse(
        Long id,
        String name,
        Integer quantity,
        String imageUrl,
        Instant createdAt,
        Instant updatedAt
) {}

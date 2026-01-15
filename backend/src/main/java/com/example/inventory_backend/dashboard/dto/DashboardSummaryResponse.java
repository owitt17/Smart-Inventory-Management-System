package com.example.inventory_backend.dashboard.dto;

public record DashboardSummaryResponse(
        long totalItems,
        long totalQuantity,
        long lowStockCount,
        long outOfStockCount,
        int lowStockThreshold
) {}

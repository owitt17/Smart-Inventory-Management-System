package com.example.inventory_backend.dashboard;

import com.example.inventory_backend.dashboard.dto.DashboardSummaryResponse;
import com.example.inventory_backend.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ItemRepository itemRepository;

    public DashboardSummaryResponse getSummary(int threshold) {
        long totalItems = itemRepository.countAllItems();
        long totalQty = itemRepository.sumAllQuantities();
        long outOfStock = itemRepository.countOutOfStock();
        long lowStock = itemRepository.countLowStock(threshold);

        return new DashboardSummaryResponse(
                totalItems,
                totalQty,
                lowStock,
                outOfStock,
                threshold
        );
    }
}

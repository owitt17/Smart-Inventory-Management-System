package com.example.inventory_backend.dashboard;

import com.example.inventory_backend.dashboard.dto.DashboardSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/summary")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<DashboardSummaryResponse> summary(
            @RequestParam(defaultValue = "5") int lowStockThreshold
    ) {
        return ResponseEntity.ok(dashboardService.getSummary(lowStockThreshold));
    }
}

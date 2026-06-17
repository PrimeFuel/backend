package com.primefuel.fulltank.platform.reporting.domain.model.valueobjects;

import java.util.List;

public record BuyerAnalytics(Long companyId, long totalOrders, double totalSpent,
                             long completedPayments, long pendingPayments,
                             List<MonthlyAmount> monthlySpending) {
}

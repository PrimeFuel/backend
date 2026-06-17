package com.primefuel.fulltank.platform.reporting.interfaces.rest.resources;

import com.primefuel.fulltank.platform.reporting.domain.model.valueobjects.MonthlyAmount;
import java.util.List;

public record ProviderAnalyticsResource(Long providerId, long totalOrders, long confirmedOrders,
                                        long cancelledOrders, double totalRevenue,
                                        List<MonthlyAmount> monthlyRevenue) {
}

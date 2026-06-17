package com.primefuel.fulltank.platform.reporting.interfaces.rest.transform;

import com.primefuel.fulltank.platform.reporting.domain.model.valueobjects.BuyerAnalytics;
import com.primefuel.fulltank.platform.reporting.interfaces.rest.resources.BuyerAnalyticsResource;

public final class BuyerAnalyticsResourceFromValueObjectAssembler {

    private BuyerAnalyticsResourceFromValueObjectAssembler() {
    }

    public static BuyerAnalyticsResource toResourceFromValueObject(BuyerAnalytics analytics) {
        return new BuyerAnalyticsResource(analytics.companyId(), analytics.totalOrders(),
                analytics.totalSpent(), analytics.completedPayments(), analytics.pendingPayments(),
                analytics.monthlySpending());
    }
}

package com.primefuel.fulltank.platform.reporting.interfaces.rest.transform;

import com.primefuel.fulltank.platform.reporting.domain.model.valueobjects.ProviderAnalytics;
import com.primefuel.fulltank.platform.reporting.interfaces.rest.resources.ProviderAnalyticsResource;

public final class ProviderAnalyticsResourceFromValueObjectAssembler {

    private ProviderAnalyticsResourceFromValueObjectAssembler() {
    }

    public static ProviderAnalyticsResource toResourceFromValueObject(ProviderAnalytics analytics) {
        return new ProviderAnalyticsResource(analytics.providerId(), analytics.totalOrders(),
                analytics.confirmedOrders(), analytics.cancelledOrders(), analytics.totalRevenue(),
                analytics.monthlyRevenue());
    }
}

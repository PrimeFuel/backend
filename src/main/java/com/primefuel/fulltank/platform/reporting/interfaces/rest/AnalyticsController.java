package com.primefuel.fulltank.platform.reporting.interfaces.rest;

import com.primefuel.fulltank.platform.reporting.application.queryservices.AnalyticsQueryService;
import com.primefuel.fulltank.platform.reporting.domain.model.queries.GetBuyerAnalyticsQuery;
import com.primefuel.fulltank.platform.reporting.domain.model.queries.GetPlatformSummaryQuery;
import com.primefuel.fulltank.platform.reporting.domain.model.queries.GetProviderAnalyticsQuery;
import com.primefuel.fulltank.platform.reporting.interfaces.rest.resources.BuyerAnalyticsResource;
import com.primefuel.fulltank.platform.reporting.interfaces.rest.resources.PlatformSummaryResource;
import com.primefuel.fulltank.platform.reporting.interfaces.rest.resources.ProviderAnalyticsResource;
import com.primefuel.fulltank.platform.reporting.interfaces.rest.transform.BuyerAnalyticsResourceFromValueObjectAssembler;
import com.primefuel.fulltank.platform.reporting.interfaces.rest.transform.PlatformSummaryResourceFromValueObjectAssembler;
import com.primefuel.fulltank.platform.reporting.interfaces.rest.transform.ProviderAnalyticsResourceFromValueObjectAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/analytics", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Analytics", description = "Analytics and reporting endpoints")
public class AnalyticsController {

    private final AnalyticsQueryService analyticsQueryService;

    public AnalyticsController(AnalyticsQueryService analyticsQueryService) {
        this.analyticsQueryService = analyticsQueryService;
    }

    @GetMapping("/platform")
    public ResponseEntity<PlatformSummaryResource> getPlatformSummary() {
        var summary = analyticsQueryService.handle(new GetPlatformSummaryQuery());
        return new ResponseEntity<>(
                PlatformSummaryResourceFromValueObjectAssembler.toResourceFromValueObject(summary),
                HttpStatus.OK);
    }

    @GetMapping("/providers/{providerId}")
    public ResponseEntity<ProviderAnalyticsResource> getProviderAnalytics(@PathVariable Long providerId) {
        var analytics = analyticsQueryService.handle(new GetProviderAnalyticsQuery(providerId));
        return new ResponseEntity<>(
                ProviderAnalyticsResourceFromValueObjectAssembler.toResourceFromValueObject(analytics),
                HttpStatus.OK);
    }

    @GetMapping("/buyers/{companyId}")
    public ResponseEntity<BuyerAnalyticsResource> getBuyerAnalytics(@PathVariable Long companyId) {
        var analytics = analyticsQueryService.handle(new GetBuyerAnalyticsQuery(companyId));
        return new ResponseEntity<>(
                BuyerAnalyticsResourceFromValueObjectAssembler.toResourceFromValueObject(analytics),
                HttpStatus.OK);
    }
}

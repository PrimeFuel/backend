package com.primefuel.fulltank.platform.catalog.domain.model.aggregates;

import com.primefuel.fulltank.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProviderRating extends AbstractDomainAggregateRoot<ProviderRating> {

    private Long id;
    private Long companyId;
    private Long providerId;
    private Integer rating;

    public ProviderRating(Long companyId, Long providerId, Integer rating) {
        this.companyId = companyId;
        this.providerId = providerId;
        changeRating(rating);
    }

    public void changeRating(Integer rating) {
        if (rating == null || rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.rating = rating;
    }
}

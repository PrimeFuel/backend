package com.primefuel.fulltank.platform.catalog.domain.infrastructure.persistence.jpa.entities;

import com.primefuel.fulltank.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "provider_ratings",
        uniqueConstraints = @UniqueConstraint(columnNames = {"company_id", "provider_id"}))
@Getter
@Setter
@NoArgsConstructor
public class ProviderRatingPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private Long providerId;

    @Column(nullable = false)
    private Integer rating;
}

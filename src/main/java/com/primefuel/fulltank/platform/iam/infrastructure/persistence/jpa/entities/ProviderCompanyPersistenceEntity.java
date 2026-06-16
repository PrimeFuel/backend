package com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.entities;

import com.primefuel.fulltank.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "provider_companies")
@Getter
@Setter
@NoArgsConstructor
public class ProviderCompanyPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, unique = true, length = 11)
    private String ruc;

    @Column
    private Double rating;

    @Column(length = 255)
    private String address;

    @Column(length = 30)
    private String phone;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "provider_company_fuel_types",
            joinColumns = @JoinColumn(name = "provider_company_id"))
    @Column(name = "fuel_type", length = 20)
    private List<String> fuelTypesOffered = new ArrayList<>();

    @Column(length = 500)
    private String description;
}

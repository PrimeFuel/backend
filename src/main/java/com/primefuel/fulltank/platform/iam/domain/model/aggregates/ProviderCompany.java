package com.primefuel.fulltank.platform.iam.domain.model.aggregates;

import com.primefuel.fulltank.platform.iam.domain.model.commands.CreateProviderCompanyCommand;
import com.primefuel.fulltank.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProviderCompany extends AbstractDomainAggregateRoot<ProviderCompany> {

    private Long id;
    private String name;
    private String ruc;
    private Double rating;
    private String address;
    private String phone;
    private List<String> fuelTypesOffered = new ArrayList<>();
    private String description;

    public ProviderCompany(CreateProviderCompanyCommand command) {
        this.name = command.name();
        this.ruc = command.ruc();
        this.rating = command.rating();
        this.address = command.address();
        this.phone = command.phone();
        this.fuelTypesOffered = command.fuelTypesOffered() != null ? new ArrayList<>(command.fuelTypesOffered()) : new ArrayList<>();
        this.description = command.description();
    }
}

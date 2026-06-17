package com.primefuel.fulltank.platform.fulfillment.domain.model.aggregates;

import com.primefuel.fulltank.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Driver extends AbstractDomainAggregateRoot<Driver> {

    private Long id;
    private Long providerId;
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private String phoneNumber;
    private String email;
    private String status;

    public Driver(Long providerId, String firstName, String lastName, String licenseNumber,
                  String phoneNumber, String email, String status) {
        update(providerId, firstName, lastName, licenseNumber, phoneNumber, email, status);
    }

    public void update(Long providerId, String firstName, String lastName, String licenseNumber,
                       String phoneNumber, String email, String status) {
        this.providerId = providerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseNumber = licenseNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.status = status;
    }
}

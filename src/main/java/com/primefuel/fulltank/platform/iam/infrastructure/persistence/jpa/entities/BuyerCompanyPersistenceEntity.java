package com.primefuel.fulltank.platform.iam.infrastructure.persistence.jpa.entities;

import com.primefuel.fulltank.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "buyer_companies")
@Getter
@Setter
@NoArgsConstructor
public class BuyerCompanyPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, unique = true, length = 11)
    private String ruc;

    @Column(length = 100)
    private String sector;

    @Column(length = 255)
    private String address;

    @Column(length = 150)
    private String contactEmail;

    @Column(length = 30)
    private String phone;
}

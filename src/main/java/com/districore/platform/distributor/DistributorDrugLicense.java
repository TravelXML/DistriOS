package com.districore.platform.distributor;

import com.districore.platform.common.TenantAwareEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "distributor_drug_licenses")
@Getter
@Setter
@NoArgsConstructor
public class DistributorDrugLicense extends TenantAwareEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String licenseNumber;
    private LocalDate validFrom;
    private LocalDate validUntil;
    private String documentUrl;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;
}

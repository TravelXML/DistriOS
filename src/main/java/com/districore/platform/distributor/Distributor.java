package com.districore.platform.distributor;

import com.districore.platform.common.TenantAwareEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "distributors")
@Getter
@Setter
@NoArgsConstructor
public class Distributor extends TenantAwareEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String email;
    private String phone;
    private String territory;

    @Enumerated(EnumType.STRING)
    private DistributorStatus status = DistributorStatus.ACTIVE;

    private BigDecimal creditLimit = BigDecimal.ZERO;

    @OneToMany(mappedBy = "distributor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<DistributorBranch> branches = new HashSet<>();
}

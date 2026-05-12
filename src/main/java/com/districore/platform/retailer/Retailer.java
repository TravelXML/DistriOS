package com.districore.platform.retailer;

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

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "retailers")
@Getter
@Setter
@NoArgsConstructor
public class Retailer extends TenantAwareEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;
    private String category;
    private String channel;
    private String phone;
    private String email;
    private String location;
    private String distributorId;
    private String beat;
    private boolean drugLicenseRequired;
    @Enumerated(EnumType.STRING)
    private RetailerStatus status = RetailerStatus.ACTIVE;

    @OneToMany(mappedBy = "retailer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<RetailerKyc> kycRecords = new HashSet<>();
}

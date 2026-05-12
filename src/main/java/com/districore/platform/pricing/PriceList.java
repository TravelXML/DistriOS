package com.districore.platform.pricing;

import com.districore.platform.common.TenantAwareEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "price_lists")
@Getter
@Setter
@NoArgsConstructor
public class PriceList extends TenantAwareEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;
    private String distributorId;
    private String retailerId;
    private String region;

    @OneToMany(mappedBy = "priceList", fetch = FetchType.LAZY)
    private Set<PriceListItem> items = new HashSet<>();
}

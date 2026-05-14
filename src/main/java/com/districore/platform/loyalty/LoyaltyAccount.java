package com.districore.platform.loyalty;

import com.districore.platform.common.TenantAwareEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "loyalty_accounts")
@Getter
@Setter
@NoArgsConstructor
public class LoyaltyAccount extends TenantAwareEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String retailerId;
    private int pointsBalance;
    private String tier;
}

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

import java.util.UUID;

@Entity
@Table(name = "distributor_branches")
@Getter
@Setter
@NoArgsConstructor
public class DistributorBranch extends TenantAwareEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String branchName;
    private String location;
    private String managerName;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;
}

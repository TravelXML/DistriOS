package com.districore.platform.tenant;

import com.districore.platform.common.TenantAwareEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "industry_configs")
@Getter
@Setter
@NoArgsConstructor
public class IndustryConfig extends TenantAwareEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IndustryVertical industryVertical;

    @Column(nullable = false)
    private boolean batchRequired;

    @Column(nullable = false)
    private boolean expiryRequired;

    @Column(nullable = false)
    private boolean hsnMandatory;

    @Column(nullable = false)
    private boolean gstMandatory;

    @Column(nullable = false)
    private boolean licenseRequired;
}

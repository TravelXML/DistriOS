package com.districore.platform.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class TenantAwareEntity extends BaseEntity {
    @Column(name = "tenant_id", nullable = false)
    private String tenantId;
}

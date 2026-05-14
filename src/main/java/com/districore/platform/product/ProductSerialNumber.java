package com.districore.platform.product;

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
@Table(name = "product_serial_numbers")
@Getter
@Setter
@NoArgsConstructor
public class ProductSerialNumber extends TenantAwareEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String serialNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}

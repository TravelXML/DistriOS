package com.districore.platform.product;

import com.districore.platform.common.TenantAwareEntity;
import com.districore.platform.tenant.IndustryVertical;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product extends TenantAwareEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String skuCode;
    @Column(nullable = false)
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uom_id")
    private Uom uom;

    private Integer packSize;
    private String packSizeUom;
    private Integer caseSize;
    private String barcode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hsn_code_id")
    private HsnCode hsnCode;
    private BigDecimal gstRate;
    private BigDecimal mrp;
    private BigDecimal basePrice;
    @Enumerated(EnumType.STRING)
    private IndustryVertical industryVertical;
    private boolean batchRequired;
    private boolean expiryRequired;
    private boolean serialNumberRequired;
    private boolean warrantyRequired;
    private String composition;
    private String manufacturerName;
    private Integer shelfLifeDays;
    private Integer warrantyMonths;
    private String regulatoryCategory;
    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.ACTIVE;
}

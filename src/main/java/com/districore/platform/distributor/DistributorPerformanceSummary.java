package com.districore.platform.distributor;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DistributorPerformanceSummary {
    private String id;
    private String name;
    private String territory;
    private String status;
    private BigDecimal creditLimit;
}

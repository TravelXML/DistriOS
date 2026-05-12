package com.districore.platform.distributor;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DistributorResponse {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String territory;
    private DistributorStatus status;
    private BigDecimal creditLimit;
}

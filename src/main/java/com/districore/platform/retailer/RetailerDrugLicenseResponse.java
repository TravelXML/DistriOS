package com.districore.platform.retailer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RetailerDrugLicenseResponse {
    private String id;
    private String licenseNumber;
    private LocalDate validFrom;
    private LocalDate validUntil;
    private String documentUrl;
}

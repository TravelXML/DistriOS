package com.districore.platform.retailer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RetailerDrugLicenseRequest {
    @NotBlank
    private String licenseNumber;
    @NotNull
    private LocalDate validFrom;
    @NotNull
    private LocalDate validUntil;
    private String documentUrl;
}

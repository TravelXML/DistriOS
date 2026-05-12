package com.districore.platform.distributor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DrugLicenseRequest {
    @NotBlank
    private String licenseNumber;
    @NotNull
    private LocalDate validFrom;
    @NotNull
    private LocalDate validUntil;
    private String documentUrl;
}

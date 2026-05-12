package com.districore.platform.retailer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RetailerKycRequest {
    @NotBlank
    private String documentType;
    @NotBlank
    private String documentNumber;
    @NotNull
    private LocalDate issuedDate;
    @NotNull
    private LocalDate expiryDate;
}

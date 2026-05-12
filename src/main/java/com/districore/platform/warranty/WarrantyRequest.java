package com.districore.platform.warranty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WarrantyRequest {
    @NotBlank
    private String serialNumber;
    @NotBlank
    private String productId;
    @NotNull
    private LocalDate validUntil;
    private String customerName;
}

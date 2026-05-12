package com.districore.platform.warranty;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class WarrantyResponse {
    private String id;
    private String serialNumber;
    private String productId;
    private LocalDate validUntil;
    private String customerName;
}

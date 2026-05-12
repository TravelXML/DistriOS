package com.districore.platform.retailer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RetailerKycResponse {
    private String id;
    private String documentType;
    private String documentNumber;
    private LocalDate issuedDate;
    private LocalDate expiryDate;
}

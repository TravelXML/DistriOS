package com.districore.platform.retailer;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RetailerDrugLicenseRepository extends JpaRepository<RetailerDrugLicense, UUID> {
}

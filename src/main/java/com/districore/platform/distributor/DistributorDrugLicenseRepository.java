package com.districore.platform.distributor;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DistributorDrugLicenseRepository extends JpaRepository<DistributorDrugLicense, UUID> {
}

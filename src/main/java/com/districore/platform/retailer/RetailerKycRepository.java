package com.districore.platform.retailer;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RetailerKycRepository extends JpaRepository<RetailerKyc, UUID> {
}

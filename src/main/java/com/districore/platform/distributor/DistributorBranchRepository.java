package com.districore.platform.distributor;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface DistributorBranchRepository extends JpaRepository<DistributorBranch, UUID> {
    List<DistributorBranch> findByDistributorIdAndTenantId(UUID distributorId, String tenantId);
}

package com.districore.platform.distributor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DistributorBranchResponse {
    private String id;
    private String branchName;
    private String location;
    private String managerName;
    private String phone;
}

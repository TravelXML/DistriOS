package com.districore.platform.distributor;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateBranchRequest {
    @NotBlank
    private String branchName;
    private String location;
    private String managerName;
    private String phone;
}

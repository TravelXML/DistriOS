package com.districore.platform.distributor;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateDistributorRequest {
    @NotBlank
    private String name;
    private String email;
    private String phone;
    private String territory;
}

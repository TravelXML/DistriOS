package com.districore.platform.distributor;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatusUpdateRequest {
    @NotNull
    private DistributorStatus status;
}

package com.districore.platform.retailer;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatusUpdateRequest {
    @NotNull
    private RetailerStatus status;
}

package com.districore.platform.inventory;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WarehouseRequest {
    @NotBlank
    private String name;
    private String location;
    private String managerName;
}

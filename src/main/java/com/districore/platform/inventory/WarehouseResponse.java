package com.districore.platform.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WarehouseResponse {
    private String id;
    private String name;
    private String location;
    private String managerName;
}

package com.districore.platform.retailer;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateRetailerRequest {
    @NotBlank
    private String name;
    private String category;
    private String channel;
    private String phone;
    private String email;
    private String location;
    private String distributorId;
    private String beat;
}

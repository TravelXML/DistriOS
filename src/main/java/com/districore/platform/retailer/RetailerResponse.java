package com.districore.platform.retailer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RetailerResponse {
    private String id;
    private String name;
    private String category;
    private String channel;
    private String phone;
    private String email;
    private String location;
    private String distributorId;
    private String beat;
    private RetailerStatus status;
}

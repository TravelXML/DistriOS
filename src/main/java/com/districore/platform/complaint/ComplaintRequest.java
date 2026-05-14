package com.districore.platform.complaint;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ComplaintRequest {
    @NotBlank
    private String retailerId;
    @NotBlank
    private String category;
    @NotBlank
    private String description;
}

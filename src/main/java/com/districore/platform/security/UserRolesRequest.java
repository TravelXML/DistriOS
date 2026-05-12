package com.districore.platform.security;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class UserRolesRequest {
    @NotEmpty
    private Set<String> roleNames;
}

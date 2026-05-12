package com.districore.platform.scheme;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SchemeResponse {
    private String id;
    private String name;
    private SchemeType type;
    private boolean active;
}

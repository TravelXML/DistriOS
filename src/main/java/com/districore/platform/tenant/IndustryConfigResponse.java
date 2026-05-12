package com.districore.platform.tenant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndustryConfigResponse {
    private String id;
    private IndustryVertical industryVertical;
    private boolean batchRequired;
    private boolean expiryRequired;
    private boolean hsnMandatory;
    private boolean gstMandatory;
    private boolean licenseRequired;
}

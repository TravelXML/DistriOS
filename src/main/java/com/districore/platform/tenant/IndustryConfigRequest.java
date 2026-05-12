package com.districore.platform.tenant;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IndustryConfigRequest {
    @NotNull
    private IndustryVertical industryVertical;
    private boolean batchRequired;
    private boolean expiryRequired;
    private boolean hsnMandatory;
    private boolean gstMandatory;
    private boolean licenseRequired;
}

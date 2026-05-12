package com.districore.platform.distributor;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreditLimitRequest {
    @NotNull
    private BigDecimal creditLimit;
}

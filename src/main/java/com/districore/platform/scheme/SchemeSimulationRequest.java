package com.districore.platform.scheme;

import com.districore.platform.order.OrderLineReq;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class SchemeSimulationRequest {
    @NotEmpty
    private List<OrderLineReq> items;
}

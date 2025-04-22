package it.mrt.bills.dtos.gas;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class GasComparisonRequestDTO {

    private BigDecimal consumption;
    private String region;

    private String priceType;

    private UUID providerId;
}

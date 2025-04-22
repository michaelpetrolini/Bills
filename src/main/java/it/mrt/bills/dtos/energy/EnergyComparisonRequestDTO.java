package it.mrt.bills.dtos.energy;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class EnergyComparisonRequestDTO {

    private BigDecimal f1Consumption;
    private BigDecimal f2Consumption;
    private BigDecimal f3Consumption;
    private BigDecimal powerSupply;

    private String offerType;
    private String priceType;
    private String contractType;

    private UUID providerId;
}

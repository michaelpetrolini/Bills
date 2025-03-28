package it.mrt.bills.dtos.energy;

import lombok.Data;

import java.util.UUID;

@Data
public class EnergyComparisonRequestDTO {

    private Double f1Consumption;
    private Double f2Consumption;
    private Double f3Consumption;
    private Double powerSupply;

    private String offerType;
    private String priceType;
    private String contractType;

    private UUID providerId;
}

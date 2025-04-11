package it.mrt.bills.dtos.gas;

import lombok.Data;

import java.util.UUID;

@Data
public class GasComparisonRequestDTO {

    private Double consumption;
    private String region;

    private String priceType;

    private UUID providerId;
}

package it.mrt.bills.dtos.energy;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class EnergyConsumptionDTO {
    private UUID id;
    private LocalDate referenceStartDate;
    private LocalDate referenceEndDate;
    private String readType;

    private Double f1Consumption;
    private Double f2Consumption;
    private Double f3Consumption;
    private Double maxPower;

    private Double f0UnitaryPrice;
    private Double f1UnitaryPrice;
    private Double f2UnitaryPrice;
    private Double f3UnitaryPrice;

    private Double f0TotalPrice;
    private Double f1TotalPrice;
    private Double f2TotalPrice;
    private Double f3TotalPrice;
    private Double networkLossesFee;

    private Double dispatchingRate;
    private Double fixedFee;
    private Double variableFee;
    private Double cdispRate;
    private Double bonus;
    private Double previousAdvances;

    private UUID billId;
}

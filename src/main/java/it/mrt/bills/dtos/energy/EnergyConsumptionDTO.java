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

    private UUID billId;
}

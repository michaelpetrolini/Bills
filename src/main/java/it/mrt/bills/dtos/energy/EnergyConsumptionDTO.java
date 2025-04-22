package it.mrt.bills.dtos.energy;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class EnergyConsumptionDTO {
    private UUID id;
    private LocalDate referenceStartDate;
    private LocalDate referenceEndDate;
    private String readType;

    private BigDecimal f1Consumption;
    private BigDecimal f2Consumption;
    private BigDecimal f3Consumption;
    private BigDecimal maxPower;

    private BigDecimal f0UnitaryPrice;
    private BigDecimal f1UnitaryPrice;
    private BigDecimal f2UnitaryPrice;
    private BigDecimal f3UnitaryPrice;

    private BigDecimal f0TotalPrice;
    private BigDecimal f1TotalPrice;
    private BigDecimal f2TotalPrice;
    private BigDecimal f3TotalPrice;
    private BigDecimal networkLossesFee;

    private BigDecimal dispatchingRate;
    private BigDecimal fixedFee;
    private BigDecimal variableFee;
    private BigDecimal cdispRate;
    private BigDecimal bonus;
    private BigDecimal previousAdvances;

    private UUID billId;
}

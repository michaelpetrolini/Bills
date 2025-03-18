package it.mrt.bills.dtos.filters;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class EnergyConsumptionFilters {
    private LocalDate referenceStartDate;
    private LocalDate referenceEndDate;
    private UUID billId;
    private String readType;
}

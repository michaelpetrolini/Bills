package it.mrt.bills.dtos.filters;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class GasConsumptionFilters {

    private LocalDate referenceStartDate;
    private LocalDate referenceEndDate;
    private UUID billId;
    private String readType;
}

package it.mrt.bills.dtos.gas;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class GasConsumptionDTO {

    private UUID id;
    private LocalDate referenceStartDate;
    private LocalDate referenceEndDate;
    private String readType;
    private BigDecimal consumption;

    private UUID billId;
}

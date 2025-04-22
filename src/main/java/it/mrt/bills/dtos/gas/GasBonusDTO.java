package it.mrt.bills.dtos.gas;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class GasBonusDTO {
    private UUID id;

    private String name;
    private String description;
    private BigDecimal value;

    private Boolean alwaysApplicable;
    private Boolean subjectToTaxation;
    private String applicabilityCondition;
    private String validity;
}

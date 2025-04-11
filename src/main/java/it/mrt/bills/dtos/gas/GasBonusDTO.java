package it.mrt.bills.dtos.gas;

import lombok.Data;

import java.util.UUID;

@Data
public class GasBonusDTO {
    private UUID id;

    private String name;
    private String description;
    private Double value;

    private Boolean alwaysApplicable;
    private Boolean subjectToTaxation;
    private String applicabilityCondition;
    private String validity;
}

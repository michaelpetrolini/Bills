package it.mrt.bills.dtos.energy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnergyComparisonResultDTO {
    private String code;
    private String name;
    private Double energyCost;
    private Double dispatchingCost;
    private Double systemCharges;
    private Double bonus;
    private Double exciseTax;
    private Double taxes;
    private Double totalAmount;
}

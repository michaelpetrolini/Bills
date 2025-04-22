package it.mrt.bills.dtos.energy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnergyComparisonResultDTO {
    private String code;
    private String name;
    private BigDecimal energyCost;
    private BigDecimal dispatchingCost;
    private BigDecimal systemCharges;
    private BigDecimal bonus;
    private BigDecimal exciseTax;
    private BigDecimal taxes;
    private BigDecimal totalAmount;
}

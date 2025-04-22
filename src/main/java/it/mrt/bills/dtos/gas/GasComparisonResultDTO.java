package it.mrt.bills.dtos.gas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GasComparisonResultDTO {

    private String code;
    private String name;
    private BigDecimal gasCost;
    private BigDecimal dispatchingCost;
    private BigDecimal systemCharges;
    private BigDecimal bonus;
    private BigDecimal vat;
    private BigDecimal taxes;
    private BigDecimal totalAmount;
}

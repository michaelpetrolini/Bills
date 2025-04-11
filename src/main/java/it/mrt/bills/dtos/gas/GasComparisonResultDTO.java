package it.mrt.bills.dtos.gas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GasComparisonResultDTO {

    private String code;
    private String name;
    private Double gasCost;
    private Double dispatchingCost;
    private Double systemCharges;
    private Double bonus;
    private Double vat;
    private Double taxes;
    private Double totalAmount;
}

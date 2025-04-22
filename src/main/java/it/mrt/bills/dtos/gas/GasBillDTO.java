package it.mrt.bills.dtos.gas;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class GasBillDTO {

    private UUID id;
    private String invoiceNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private String url;

    private BigDecimal gasSupplyTotalCost;
    private BigDecimal gasSupplyFixedCost;
    private BigDecimal gasCost;

    private BigDecimal deliveryAndManagementCost;
    private BigDecimal deliveryAndManagementFixedCost;
    private BigDecimal deliveryAndManagementVariableCost;

    private BigDecimal recalculations;
    private BigDecimal otherCosts;
    private BigDecimal socialBonus;
    private BigDecimal taxes;

    private UUID userId;
    private UUID offerId;
    private UUID contractId;
}

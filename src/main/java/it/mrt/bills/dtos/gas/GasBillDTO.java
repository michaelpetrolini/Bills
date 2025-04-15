package it.mrt.bills.dtos.gas;

import lombok.Data;

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

    private Double gasSupplyTotalCost;
    private Double gasSupplyFixedCost;
    private Double gasCost;

    private Double deliveryAndManagementCost;
    private Double deliveryAndManagementFixedCost;
    private Double deliveryAndManagementVariableCost;

    private Double recalculations;
    private Double otherCosts;
    private Double socialBonus;
    private Double taxes;

    private UUID userId;
    private UUID offerId;
    private UUID contractId;
}

package it.mrt.bills.dtos.energy;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class EnergyBillDTO {
    private UUID id;
    private String invoiceNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private String address;
    private String url;


    //Cost section
    private BigDecimal totalCost;

    //Energy costs
    private BigDecimal energySupplyCost;

    private BigDecimal deliveryAndManagementCost;
    private BigDecimal systemCharges;
    private BigDecimal otherCharges;
    private BigDecimal taxes;
    private BigDecimal tvSubscriptionFee;
    private BigDecimal latePaymentInterest;
    private BigDecimal previouslyUnpaidInvoicesCharges;
    private BigDecimal contractualCharges;
    private BigDecimal disconnectionReconnectionCost;
    private BigDecimal securityDeposit;

    private BigDecimal discount;
    private BigDecimal recalculations;

    private BigDecimal energyConsumption;


    private UUID userId;
    private UUID contractId;
    private UUID offerId;
}

package it.mrt.bills.dtos.energy;

import lombok.Data;

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
    private Double totalCost;

    //Energy costs
    private Double energySupplyCost;

    private Double deliveryAndManagementCost;
    private Double systemCharges;
    private Double otherCharges;
    private Double taxes;
    private Double tvSubscriptionFee;
    private Double latePaymentInterest;
    private Double previouslyUnpaidInvoicesCharges;
    private Double contractualCharges;
    private Double disconnectionReconnectionCost;
    private Double securityDeposit;

    private Double discount;
    private Double recalculations;

    private Double energyConsumption;


    private UUID userId;
    private UUID contractId;
    private UUID offerId;
}

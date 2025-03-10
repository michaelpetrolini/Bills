package it.mrt.bills.entities.energy;

import it.mrt.bills.entities.DbEntity;
import it.mrt.bills.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class EnergyBill extends DbEntity {

    private String invoiceNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private Type type;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "offer_id")
    private EnergyOffer energyOffer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
    private EnergyContract energyContract;


    public enum Type {
        REGULAR,
        REGULAR_WITH_ADJUSTMENT,
        ADJUSTMENT,
        FINAL,
        PROVISIONAL_FINAL,
        EXTRAORDINARY
    }
}

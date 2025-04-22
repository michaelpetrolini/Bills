package it.mrt.bills.entities.energy;

import it.mrt.bills.entities.DbEntity;
import it.mrt.bills.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
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
    @Column(precision = 10, scale = 2)
    private BigDecimal totalCost;

    //Energy costs
    @Column(precision = 10, scale = 2)
    private BigDecimal energySupplyCost;

    @Column(precision = 10, scale = 2)
    private BigDecimal deliveryAndManagementCost;
    @Column(precision = 10, scale = 2)
    private BigDecimal systemCharges;
    @Column(precision = 10, scale = 2)
    private BigDecimal otherCharges;
    @Column(precision = 10, scale = 2)
    private BigDecimal taxes;
    @Column(precision = 10, scale = 2)
    private BigDecimal tvSubscriptionFee;
    @Column(precision = 10, scale = 2)
    private BigDecimal latePaymentInterest;
    @Column(precision = 10, scale = 2)
    private BigDecimal previouslyUnpaidInvoicesCharges;
    @Column(precision = 10, scale = 2)
    private BigDecimal contractualCharges;
    @Column(precision = 10, scale = 2)
    private BigDecimal disconnectionReconnectionCost;
    @Column(precision = 10, scale = 2)
    private BigDecimal securityDeposit;

    @Column(precision = 10, scale = 2)
    private BigDecimal discount;
    @Column(precision = 10, scale = 2)
    private BigDecimal recalculations;

    @Column(precision = 10, scale = 5)
    private BigDecimal energyConsumption;

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

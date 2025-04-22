package it.mrt.bills.entities.gas;

import it.mrt.bills.entities.DbEntity;
import it.mrt.bills.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class GasBill extends DbEntity {

    private String invoiceNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private String url;


    //Costs
    @Column(precision = 10, scale = 2)
    private BigDecimal gasSupplyTotalCost;
    @Column(precision = 10, scale = 2)
    private BigDecimal gasSupplyFixedCost;
    @Column(precision = 10, scale = 2)
    private BigDecimal gasCost;

    @Column(precision = 10, scale = 2)
    private BigDecimal deliveryAndManagementCost;
    @Column(precision = 10, scale = 2)
    private BigDecimal deliveryAndManagementFixedCost;
    @Column(precision = 10, scale = 2)
    private BigDecimal deliveryAndManagementVariableCost;

    @Column(precision = 10, scale = 2)
    private BigDecimal recalculations;
    @Column(precision = 10, scale = 2)
    private BigDecimal otherCosts;
    @Column(precision = 10, scale = 2)
    private BigDecimal socialBonus;
    @Column(precision = 10, scale = 2)
    private BigDecimal taxes;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "offer_id")
    private GasOffer gasOffer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
    private GasContract gasContract;
    @OneToMany(mappedBy = "gasBill", fetch = FetchType.LAZY)
    private Set<GasConsumption> gasConsumptions;
}

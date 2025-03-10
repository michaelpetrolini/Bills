package it.mrt.bills.entities.gas;

import it.mrt.bills.entities.DbEntity;
import it.mrt.bills.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

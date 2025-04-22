package it.mrt.bills.entities.energy;

import it.mrt.bills.entities.DbEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class EnergyConsumption extends DbEntity {

    private LocalDate referenceStartDate;
    private LocalDate referenceEndDate;
    @Enumerated(EnumType.STRING)
    private ReadType readType;

    @Column(name = "f1_consumption", precision = 10, scale = 5)
    private BigDecimal f1Consumption;
    @Column(name = "f2_consumption", precision = 10, scale = 5)
    private BigDecimal f2Consumption;
    @Column(name = "f3_consumption", precision = 10, scale = 5)
    private BigDecimal f3Consumption;
    @Column(precision = 10, scale = 5)
    private BigDecimal maxPower;

    @Column(name = "f0_unitary_price", precision = 10, scale = 9)
    private BigDecimal f0UnitaryPrice;
    @Column(name = "f1_unitary_price", precision = 10, scale = 9)
    private BigDecimal f1UnitaryPrice;
    @Column(name = "f2_unitary_price", precision = 10, scale = 9)
    private BigDecimal f2UnitaryPrice;
    @Column(name = "f3_unitary_price", precision = 10, scale = 9)
    private BigDecimal f3UnitaryPrice;

    @Column(name = "f0_total_price", precision = 10, scale = 2)
    private BigDecimal f0TotalPrice;
    @Column(name = "f1_total_price", precision = 10, scale = 2)
    private BigDecimal f1TotalPrice;
    @Column(name = "f2_total_price", precision = 10, scale = 2)
    private BigDecimal f2TotalPrice;
    @Column(name = "f3_total_price", precision = 10, scale = 2)
    private BigDecimal f3TotalPrice;
    @Column(name = "network_losses_fee", precision = 10, scale = 2)
    private BigDecimal networkLossesFee;

    @Column(precision = 10, scale = 2)
    private BigDecimal dispatchingRate;
    @Column(precision = 10, scale = 2)
    private BigDecimal fixedFee;
    @Column(precision = 10, scale = 2)
    private BigDecimal variableFee;
    @Column(precision = 10, scale = 2)
    private BigDecimal cdispRate;
    @Column(precision = 10, scale = 5)
    private BigDecimal bonus;
    @Column(precision = 10, scale = 2)
    private BigDecimal previousAdvances;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bill_id")
    private EnergyBill energyBill;


    public enum ReadType {
        DETECTION,
        ESTIMATE
    }
}

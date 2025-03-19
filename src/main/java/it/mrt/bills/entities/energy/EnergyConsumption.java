package it.mrt.bills.entities.energy;

import it.mrt.bills.entities.DbEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class EnergyConsumption extends DbEntity {

    private LocalDate referenceStartDate;
    private LocalDate referenceEndDate;
    @Enumerated(EnumType.STRING)
    private ReadType readType;

    private Double f1Consumption;
    private Double f2Consumption;
    private Double f3Consumption;
    private Double maxPower;

    private Double f0UnitaryPrice;
    private Double f1UnitaryPrice;
    private Double f2UnitaryPrice;
    private Double f3UnitaryPrice;

    private Double f0TotalPrice;
    private Double f1TotalPrice;
    private Double f2TotalPrice;
    private Double f3TotalPrice;
    private Double networkLossesFee;

    private Double dispatchingRate;
    private Double fixedFee;
    private Double variableFee;
    private Double cdispRate;
    private Double bonus;
    private Double previousAdvances;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bill_id")
    private EnergyBill energyBill;


    public enum ReadType {
        DETECTION,
        ESTIMATE
    }
}

package it.mrt.bills.entities.energy;

import it.mrt.bills.entities.DbEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class EnergyConsumption extends DbEntity {

    private LocalDate referenceStartDate;
    private LocalDate referenceEndDate;
    private ReadType readType;

    private Double f1Consumption;
    private Double f2Consumption;
    private Double f3Consumption;

    private Double maxPower;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bill_id")
    private EnergyBill energyBill;


    public enum ReadType {
        DETECTION,
        ESTIMATE
    }
}

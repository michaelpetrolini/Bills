package it.mrt.bills.entities.gas;

import it.mrt.bills.entities.DbEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class GasConsumption extends DbEntity {

    private LocalDate referenceStartDate;
    private LocalDate referenceEndDate;
    @Enumerated(EnumType.STRING)
    private ReadType readType;

    private Double consumption;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bill_id")
    private GasBill gasBill;

    public enum ReadType {
        DETECTION,
        ESTIMATE
    }
}

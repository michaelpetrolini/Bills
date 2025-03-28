package it.mrt.bills.entities.energy;

import it.mrt.bills.entities.DbEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EnergyBonus extends DbEntity {

    private String name;
    @Column(columnDefinition="text")
    private String description;
    private Double value;

    private Boolean alwaysApplicable;
    private Boolean subjectToTaxation;
    @Enumerated(EnumType.STRING)
    private ApplicabilityCondition applicabilityCondition;
    @Enumerated(EnumType.STRING)
    private Validity validity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="offer_id")
    private EnergyOffer energyOffer;


    public enum ApplicabilityCondition {
        ALWAYS_APPLICABLE,
        ELECTRONIC_INVOICING,
        ONLINE_MANAGEMENT,
        DIRECT_DEBIT,
        ELECTRONIC_INVOICING_AND_DIRECT_DEBIT
    }

    public enum Validity {
        UPON_SUBSCRIPTION,
        WITHIN_12_MONTHS,
        OVER_12_MONTHS
    }
}

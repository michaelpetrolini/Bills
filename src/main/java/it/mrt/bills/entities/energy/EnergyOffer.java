package it.mrt.bills.entities.energy;

import it.mrt.bills.entities.CommonParameter;
import it.mrt.bills.entities.DbEntity;
import it.mrt.bills.entities.Provider;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class EnergyOffer extends DbEntity {

    private String code;
    private String name;
    private LocalDate validFrom;
    private LocalDate validTo;
    @Enumerated(EnumType.STRING)
    private OfferType offerType;
    @Enumerated(EnumType.STRING)
    private PriceType priceType;
    @Enumerated(EnumType.STRING)
    private ContractType contractType;
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    private Double singleRate;
    private Double f1Rate;
    private Double f2Rate;
    private Double f3Rate;
    private Double f2f3Rate;
    private Double f1f2Rate;
    private Double dispatchingRate;
    private Double energyFixedFee;


    private Double commercializationCosts;

    private Double annualDiscount;
    private Double oneTimeDiscount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="energy_offer_common_parameter",
            joinColumns=@JoinColumn(name="energy_offer_id"),
            inverseJoinColumns=@JoinColumn(name="common_parameter_id"))
    private Set<CommonParameter> commonParameters;

    public enum PriceType {
        FIXED,
        VARIABLE,
        FLAT
    }

    public enum ContractType {
        TRIHOURLY,
        PUN_INDEX_GME,
        BIHOURLY_F1_F23,
        BIHOURLY_F12_F3,
        MONOHOURLY
    }

    public enum OfferType {
        PLACET
    }

    public enum CustomerType {
        DOMESTIC,
        OTHER_USES
    }
}

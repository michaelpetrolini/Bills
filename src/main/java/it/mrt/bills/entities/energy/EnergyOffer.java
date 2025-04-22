package it.mrt.bills.entities.energy;

import it.mrt.bills.entities.CommonParameter;
import it.mrt.bills.entities.DbEntity;
import it.mrt.bills.entities.Provider;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
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
    private Boolean dualOffer;
    @Enumerated(EnumType.STRING)
    private OfferType offerType;
    @Enumerated(EnumType.STRING)
    private PriceType priceType;
    @Enumerated(EnumType.STRING)
    private ContractType contractType;
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @Column(precision = 20, scale = 10)
    private BigDecimal singleRate;
    @Column(name = "f1_rate", precision = 20, scale = 10)
    private BigDecimal f1Rate;
    @Column(name = "f2_rate", precision = 20, scale = 10)
    private BigDecimal f2Rate;
    @Column(name = "f3_rate", precision = 20, scale = 10)
    private BigDecimal f3Rate;
    @Column(name = "f2f3_rate", precision = 20, scale = 10)
    private BigDecimal f2f3Rate;
    @Column(name = "f1f2_rate", precision = 20, scale = 10)
    private BigDecimal f1f2Rate;
    @Column(precision = 20, scale = 10)
    private BigDecimal dispatchingRate;
    @Column(precision = 20, scale = 10)
    private BigDecimal energyFixedFee;
    @Column(precision = 20, scale = 10)
    private BigDecimal commercializationCosts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="energy_offer_common_parameter",
            joinColumns=@JoinColumn(name="energy_offer_id"),
            inverseJoinColumns=@JoinColumn(name="common_parameter_id"))
    private Set<CommonParameter> commonParameters;

    @OneToMany(mappedBy = "energyOffer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EnergyBonus> energyBonuses;

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
        PLACET,
        FREE_MARKET
    }

    public enum CustomerType {
        DOMESTIC,
        OTHER_USES
    }
}

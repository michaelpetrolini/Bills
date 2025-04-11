package it.mrt.bills.entities.gas;

import it.mrt.bills.entities.CommonParameter;
import it.mrt.bills.entities.DbEntity;
import it.mrt.bills.entities.Provider;
import it.mrt.bills.entities.Region;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class GasOffer extends DbEntity {

    private String code;
    private String name;
    private LocalDate validFrom;
    private LocalDate validTo;
    private Boolean dualOffer;
    private Boolean hasLimitations;

    private Double singleRate;
    private Double gasFixedFee;

    @Enumerated(EnumType.STRING)
    private OfferType offerType;
    @Enumerated(EnumType.STRING)
    private PriceType priceType;
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="gas_offer_common_parameter",
            joinColumns=@JoinColumn(name="gas_offer_id"),
            inverseJoinColumns=@JoinColumn(name="common_parameter_id"))
    private Set<CommonParameter> commonParameters;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="gas_offer_region",
            joinColumns=@JoinColumn(name="gas_offer_id"),
            inverseJoinColumns=@JoinColumn(name="region_id"))
    private Set<Region> regions;

    @OneToMany(mappedBy = "gasOffer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GasBonus> gasBonuses;

    public enum OfferType {
        PLACET,
        FREE_MARKET
    }

    public enum PriceType {
        FIXED,
        VARIABLE,
        FLAT
    }

    public enum CustomerType {
        DOMESTIC,
        OTHER_USES,
        CONDOMINIUM
    }
}

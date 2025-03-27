package it.mrt.bills.entities;

import it.mrt.bills.entities.energy.EnergyOffer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class CommonParameter extends DbEntity {

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private ParameterType name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Double value;

    @ManyToMany(mappedBy = "commonParameters", fetch = FetchType.LAZY)
    private Set<EnergyOffer> energyOffers;

    public enum ParameterType {
        DISPATCHING_FEE,
        GREATER_PROTECTION,
        DISPATCHING_SERVICE_MARKET,
        WIND_MODULATION,
        ESSENTIAL_UNITS,
        TERNA_OPERATION,
        PRODUCT_CAPACITY,
        INTERRUPTIBILITY,
        STG_CAPACITY_COST,
        MARKET_CAPACITY,
        SAFEGUARD_COSTS,
        GRADUAL_PROTECTION_COSTS,
        DISPBT,
        FREE_MARKET_DISPATCHING,
        ASOS,
        ARIM,
        SIGMA1,
        SIGMA2,
        SIGMA3,
        UC3,
        UC6_FIRST,
        UC6_SECOND,
        EXCISE_TAX
    }

    public enum Category {
        ENERGY_FIXED,
        ENERGY_VARIABLE,
        DISPATCHING_FIXED_QUOTA,
        DISPATCHING_POWER_QUOTA,
        DISPATCHING_ENERGY_QUOTA,
        SYSTEM_CHARGES_FIXED_QUOTA,
        SYSTEM_CHARGES_POWER_QUOTA,
        SYSTEM_CHARGES_ENERGY_QUOTA,
        TAXES_FIXED,
        TAXES_VARIABLE
    }
}

package it.mrt.bills.entities;

import it.mrt.bills.entities.energy.EnergyOffer;
import it.mrt.bills.entities.gas.GasOffer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
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
    @Column(precision = 20, scale = 10)
    private BigDecimal value;

    private Integer start;
    @Column(name = "`end`")
    private Integer end;

    @ManyToMany(mappedBy = "commonParameters", fetch = FetchType.LAZY)
    private Set<EnergyOffer> energyOffers;

    @ManyToMany(mappedBy = "commonParameters", fetch = FetchType.LAZY)
    private Set<GasOffer> gasOffers;

    public enum ParameterType {
        // Energy
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
        EXCISE_TAX,

        // Gas
        TAU1,
        ST,
        VR,
        QT,
        TAU_3_120,
        TAU_3_480,
        TAU_3_1560,
        TAU_3_5000,
        RS,
        UG1,
        UG2_2,
        RE,
        UG2_120,
        UG2_480,
        UG2_1560,
        UG2_5000,
        UG3,
        GAS_EXCISE_TAX_120,
        GAS_EXCISE_TAX_480,
        GAS_EXCISE_TAX_1560,
        GAS_EXCISE_TAX_MAX,
        GAS_REGIONAL_TAX_120,
        GAS_REGIONAL_TAX_480,
        GAS_REGIONAL_TAX_1560,
        GAS_REGIONAL_TAX_MAX,
        CCR_AVERAGE,
        QVD_FIXED,
        QVD_VARIABLE,
        GRAD,
        QTINT,
        QTPSV,
        CPR
    }

    public enum Category {
        // Energy
        ENERGY_FIXED,
        ENERGY_VARIABLE,
        ENERGY_DISPATCHING_FIXED_QUOTA,
        ENERGY_DISPATCHING_POWER_QUOTA,
        ENERGY_DISPATCHING_ENERGY_QUOTA,
        ENERGY_SYSTEM_CHARGES_FIXED_QUOTA,
        ENERGY_SYSTEM_CHARGES_POWER_QUOTA,
        ENERGY_SYSTEM_CHARGES_ENERGY_QUOTA,
        ENERGY_TAXES_FIXED,
        ENERGY_TAXES_VARIABLE,

        // Gas
        GAS_FIXED,
        GAS_VARIABLE,
        GAS_DISPATCHING_FIXED_QUOTA,
        GAS_DISPATCHING_ENERGY_QUOTA,
        GAS_SYSTEM_CHARGES_FIXED_QUOTA,
        GAS_SYSTEM_CHARGES_ENERGY_QUOTA,
        GAS_TAXES_FIXED,
        GAS_TAXES_VARIABLE
    }
}

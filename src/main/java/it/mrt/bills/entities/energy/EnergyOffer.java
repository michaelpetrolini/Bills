package it.mrt.bills.entities.energy;

import it.mrt.bills.entities.DbEntity;
import it.mrt.bills.entities.Provider;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class EnergyOffer extends DbEntity {

    private String code;
    private String name;
    private LocalDate validFrom;
    private LocalDate validTo;
    @Enumerated(EnumType.STRING)
    private PriceType priceType;
    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    private Double singleRate;
    private Double f1Rate;
    private Double f2Rate;
    private Double f3Rate;
    private Double commercializationCosts;
    private Double dispatchingRate;
    private Double dispatchingRateFixed;
    private Double bonus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    public enum PriceType {
        FIXED,
        VARIABLE
    }

    public enum ContractType {
        BIHOURLY,
        MONOHOURLY,
        TRADITIONAL
    }}

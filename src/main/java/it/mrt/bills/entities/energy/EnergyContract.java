package it.mrt.bills.entities.energy;

import it.mrt.bills.entities.DbEntity;
import it.mrt.bills.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class EnergyContract extends DbEntity {

    private LocalDate activationDate;
    private LocalDate expirationDate;
    private String address;
    private String podCode;
    private String customerCode;
    private Integer voltageSupply;
    @Column(precision = 3, scale = 1)
    private BigDecimal contractuallyCommittedPower;
    @Column(precision = 3, scale = 1)
    private BigDecimal availablePower;
    @Column(precision = 4, scale = 2)
    private BigDecimal maxPower;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "offer_id")
    private EnergyOffer energyOffer;


    public enum CustomerType {
        RESIDENT_DOMESTIC,
        NON_RESIDENT_DOMESTIC,
        BUSINESS
    }
}

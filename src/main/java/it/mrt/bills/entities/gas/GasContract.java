package it.mrt.bills.entities.gas;

import it.mrt.bills.entities.DbEntity;
import it.mrt.bills.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class GasContract extends DbEntity {

    private LocalDate activationDate;
    private LocalDate expirationDate;
    private String address;
    private String pdrCode;
    private String customerCode;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "gasContract", fetch = FetchType.LAZY)
    private Set<GasBill> gasBills;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "offer_id")
    private GasOffer gasOffer;
}

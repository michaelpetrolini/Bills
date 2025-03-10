package it.mrt.bills.entities.gas;

import it.mrt.bills.entities.DbEntity;
import it.mrt.bills.entities.Provider;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class GasOffer extends DbEntity {

    private String code;
    private String name;
    private LocalDate validFrom;
    private LocalDate validTo;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provider_id")
    private Provider provider;
}

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
public class EnergyForecast extends DbEntity {

    private LocalDate date;
    @Column(name = "f1_consumption", precision = 10, scale = 5)
    private BigDecimal f1Consumption;
    @Column(name = "f2_consumption", precision = 10, scale = 5)
    private BigDecimal f2Consumption;
    @Column(name = "f3_consumption", precision = 10, scale = 5)
    private BigDecimal f3Consumption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}

package it.mrt.bills.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CommonParameter extends DbEntity {

    @Column(unique = true, nullable = false)
    private String name;
    private Double value;
}

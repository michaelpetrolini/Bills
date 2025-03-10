package it.mrt.bills.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Provider extends DbEntity {

    private String name;
    private String address;
}

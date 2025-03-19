package it.mrt.bills.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Provider extends DbEntity {

    private String name;
    private String code;
    private String taxCode;
    private String vatNumber;
    private String email;
    private String address;
    private String website;
    private String customerSupport;
}

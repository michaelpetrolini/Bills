package it.mrt.bills.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "`user`")
@Getter
@Setter
public class User extends DbEntity {

    private String taxCode;
    private String name;
    private String surname;

    private String email;
    private String username;
    private String password;
}

package it.mrt.bills.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private String taxCode;
    private String name;
    private String surname;
}

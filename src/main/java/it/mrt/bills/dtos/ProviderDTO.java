package it.mrt.bills.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class ProviderDTO {
    private UUID id;
    private String name;
    private String code;
    private String taxCode;
    private String vatNumber;
    private String email;
    private String address;
    private String website;
    private String customerSupport;
}
